package com.seven.springspringbootextentions.extentions;

import com.seven.springspringbootextentions.util.MatchClassUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;

@Component
public class Test4InstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (MatchClassUtils.isMatchClass(beanClass)) {
            System.out.println("进入[Test4InstantiationAwareBeanPostProcessor]...postProcessBeforeInstantiation..." + beanName);
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (MatchClassUtils.isMatchClass(bean.getClass())) {
            System.out.println("进入[Test4InstantiationAwareBeanPostProcessor]...postProcessAfterInstantiation..." + beanName);
        }
        return true;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        if (MatchClassUtils.isMatchClass(bean.getClass())) {
            System.out.println("进入[Test4InstantiationAwareBeanPostProcessor]...postProcessPropertyValues..." + beanName);
        }
        return pvs;
    }

    //BeanPostProcessor的方法
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (MatchClassUtils.isMatchClass(bean.getClass())) {
            System.out.println("进入[Test4InstantiationAwareBeanPostProcessor]...postProcessBeforeInitialization..." + beanName);
        }
        return bean;
    }

    //BeanPostProcessor的方法
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (MatchClassUtils.isMatchClass(bean.getClass())) {
            System.out.println("进入[Test4InstantiationAwareBeanPostProcessor]...postProcessAfterInitialization..." + beanName);
        }
        return bean;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if (MatchClassUtils.isMatchClass(bean.getClass())) {
            System.out.println("进入[Test4InstantiationAwareBeanPostProcessor]...postProcessProperties..." + beanName);
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessProperties(pvs, bean, beanName);
    }

}