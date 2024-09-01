package com.seven.aoplimitrepeatsubmitantishake.accesslimit;

/**
 * @author Seven
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;


/**
 * 接口限流
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AccessLimit {


    /**
     * 限制时间窗口间隔长度，默认10秒
     */
    int times() default 10;

    /**
     * 时间单位
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * 上述时间窗口内允许的最大请求数量，默认为5次
     */
    int maxCount() default 5;



    /**
     * redis key 的前缀
     */
    String preKey();

    /**
     * 提示语
     */
    String msg() default "服务请求达到最大限制，请求被拒绝！";
}
