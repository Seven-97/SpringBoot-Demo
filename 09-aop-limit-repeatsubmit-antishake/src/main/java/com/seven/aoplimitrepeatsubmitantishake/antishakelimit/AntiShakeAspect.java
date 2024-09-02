package com.seven.aoplimitrepeatsubmitantishake.antishakelimit;

import cn.hutool.crypto.digest.DigestUtil;
import com.seven.aoplimitrepeatsubmitantishake.util.IPUtil;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author Seven
 */
@Aspect // 标记为切面类
@Component // 让Spring管理这个Bean
@RequiredArgsConstructor // 通过构造方法注入依赖
public class AntiShakeAspect {

    private final String ANTI_SHAKE_LOCK_KEY = "ANTI_SHAKE_LOCK_KEY";

    private final RedissonClient redissonClient;

    @Around("@annotation(antiShake)") // 拦截所有标记了@AntiShake的方法
    public Object aroundAdvice(ProceedingJoinPoint joinPoint, AntiShake antiShake) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        long currentTime = System.currentTimeMillis();

        // 获取方法签名和参数作为 Redis 键
        String ipAddr = IPUtil.getIpAddr(request);
        String key = generateTokenRedisKey(joinPoint, ipAddr, antiShake.preKey());

        RBucket<Long> bucket = redissonClient.getBucket(key);
        Long lastTime = bucket.get();

        if (lastTime != null && currentTime - lastTime < antiShake.value()) {
            // 如果距离上次调用时间小于指定的防抖时间，则直接返回，不执行方法
            return null; // 根据业务需要返回特定值
        }

        // 更新 Redis 中的时间戳
        bucket.set(currentTime, antiShake.value(), antiShake.timeUnit());
        return joinPoint.proceed(); // 执行原方法
    }

    private String generateTokenRedisKey(ProceedingJoinPoint joinPoint, String ipAddr, String preKey) {
        //根据ip地址、用户id、类名方法名、生成唯一的key
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        String className = method.getDeclaringClass().getName();
        String userId = "seven";
        return String.format("%s:%s:%s", ANTI_SHAKE_LOCK_KEY, preKey, DigestUtil.md5Hex(String.format("%s-%s-%s-%s", ipAddr, className, method, userId)));
    }
}
