package com.seven.aoplimitrepeatsubmitantishake.repeatlimit;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 自定义接口防重注解类
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatSubmit {
    /**
     * 定义了两种防止重复提交的方式，PARAM 表示基于方法参数来防止重复，TOKEN 则可能涉及生成和验证token的机制
     */
    enum Type { PARAM, TOKEN }

    /**
     * 设置默认的防重提交方式为基于方法参数。可以不指定此参数，使用默认值。
     * @return Type
     */
    Type limitType() default Type.PARAM;

    /**
     * 允许设置加锁的过期时间，默认为5秒。这意味着在第一次请求之后的5秒内，相同的请求将被视为重复并被阻止
     */
    long lockTime() default 5;

    /**
     * 时间单位
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * redis key 的前缀
     */
    String preKey();

    //提供了一个可选的服务ID参数，通过token时用作KEY计算
    String serviceId() default "";

    /**
     * 提示语
     */
    String msg() default "请求重复提交！";
}
