package com.seven.aoplimitrepeatsubmitantishake.antishakelimit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author Seven
 */
@Aspect // 标记为切面类
@Component // 让Spring管理这个Bean
public class AntiShakeAspect {

    private ThreadLocal<Long> lastInvokeTime = new ThreadLocal<>();

    @Around("@annotation(antiShake)") // 拦截所有标记了@AntiShake的方法
    public Object aroundAdvice(ProceedingJoinPoint joinPoint, AntiShake antiShake) throws Throwable {
        long currentTime = System.currentTimeMillis();

        long lastTime = lastInvokeTime.get() != null ? lastInvokeTime.get() : 0;

        if (currentTime - lastTime < antiShake.value()) {
            // 如果距离上次调用时间小于指定的防抖时间，则直接返回，不执行方法
            return null; // 或者根据业务需要返回特定值
        }

        lastInvokeTime.set(currentTime);
        return joinPoint.proceed(); // 执行原方法
    }
}
