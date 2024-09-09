package com.seven.controllervalidation.util;



import com.seven.controllervalidation.enums.BasicEnum;
import com.seven.controllervalidation.enums.InEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author chenghaoy.yu
 * @description
 * @email chenghaoy.yu@qunar.com
 * @Date 2024/7/26 13:45
 */
public class InEnumValidator implements ConstraintValidator<InEnum, Object> {
    private Class<? extends BasicEnum> enumType;

    @Override
    public void initialize(InEnum inEnum) {
        enumType = inEnum.enumType();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        if (object == null) {
            return true;
        }

        if (enumType == null || !enumType.isEnum()) {
            return false;
        }

        for (BasicEnum basicEnum : enumType.getEnumConstants()) {
            if (basicEnum.getEnumCode().equals(object)) {
                return true;
            }
        }
        return false;
    }
}
