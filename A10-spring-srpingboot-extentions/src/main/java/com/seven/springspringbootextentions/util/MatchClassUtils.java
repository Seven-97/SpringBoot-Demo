package com.seven.springspringbootextentions.util;

import com.seven.springspringbootextentions.domain.TestUser;
import org.springframework.util.ClassUtils;


public class MatchClassUtils {

    public static boolean isMatchClass(Class<?> beanClass) {
        return TestUser.class.equals(ClassUtils.getUserClass(beanClass));
    }

}
