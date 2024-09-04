package com.seven.springspringbootextentions.extentions;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;

/**
 * @author chenghaoy.yu
 * @description
 * @email chenghaoy.yu@qunar.com
 * @Date 2024/9/4 18:38
 */
public class Test5SmartInstantiationAwareBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {
    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
        System.out.println("进入[Test5SmartInstantiationAwareBeanPostProcessor]...getEarlyBeanReference..." + bean + "..." + beanName);
        return bean;
    }


}
