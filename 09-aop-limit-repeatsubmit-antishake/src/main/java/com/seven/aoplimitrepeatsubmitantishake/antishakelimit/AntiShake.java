package com.seven.aoplimitrepeatsubmitantishake.antishakelimit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

// 该注解只能用于方法
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)// 运行时保留，这样才能在AOP中被检测到
public @interface AntiShake {

    String preKey() default "";

    // 默认防抖时间1秒
    long value() default 5000L;

    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;
}
