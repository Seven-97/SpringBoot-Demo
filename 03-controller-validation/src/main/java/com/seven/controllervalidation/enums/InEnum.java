package com.seven.controllervalidation.enums;

import com.seven.controllervalidation.utils.InEnumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author chenghaoy.yu
 * @description
 * @email chenghaoy.yu@qunar.com
 * @Date 2024/7/26 13:29
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = InEnumValidator.class)
public @interface InEnum {
    /**
     * 枚举类型
     *
     * @return
     */
    Class<? extends BasicEnum> enumType();

    String message() default "枚举类型不匹配";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}