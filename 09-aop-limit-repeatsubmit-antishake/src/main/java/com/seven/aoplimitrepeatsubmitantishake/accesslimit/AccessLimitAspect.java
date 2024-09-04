package com.seven.aoplimitrepeatsubmitantishake.accesslimit;

/**
 * @author Seven
 */

import cn.hutool.crypto.digest.DigestUtil;
import com.seven.aoplimitrepeatsubmitantishake.exception.LimitException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * 通过AOP实现接口限流
 */
@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class AccessLimitAspect {

    private static final String ACCESS_LIMIT_LOCK_KEY = "ACCESS_LIMIT_LOCK_KEY";

    private final RedissonClient redissonClient;

    @Around("@annotation(accessLimit)")
    public Object around(ProceedingJoinPoint point, AccessLimit accessLimit) throws Throwable {

        String prefix = accessLimit.preKey();
        String key = generateRedisKey(point, prefix);

        //限制窗口时间
        int time = accessLimit.times();
        //获取注解中的令牌数
        int maxCount = accessLimit.maxCount();
        //获取注解中的时间单位
        TimeUnit timeUnit = accessLimit.timeUnit();

        //分布式计数器
        RAtomicLong atomicLong = redissonClient.getAtomicLong(key);

        if (!atomicLong.isExists() || atomicLong.remainTimeToLive() <= 0) {
            atomicLong.expire(time, timeUnit);
        }

        long count = atomicLong.incrementAndGet();

        if (count > maxCount) {
            throw new LimitException(accessLimit.msg());
        }

        // 继续执行目标方法
        return point.proceed();
    }

    public String generateRedisKey(ProceedingJoinPoint point, String prefix) {
        //获取方法签名
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        //获取方法
        Method method = methodSignature.getMethod();
        //获取全类名
        String className = method.getDeclaringClass().getName();

        // 构建Redis中的key，加入类名、方法名以区分不同接口的限制
        return String.format("%s:%s:%s", ACCESS_LIMIT_LOCK_KEY, prefix, DigestUtil.md5Hex(String.format("%s-%s", className, method)));
    }
}
