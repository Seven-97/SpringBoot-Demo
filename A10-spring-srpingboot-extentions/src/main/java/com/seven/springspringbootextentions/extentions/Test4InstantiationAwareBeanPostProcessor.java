package com.seven.springspringbootextentions.extentions;

import com.seven.springspringbootextentions.domain.TestUser;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;

@Component
public class Test4InstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (isMatchClass(beanClass)) {
            System.out.println("进入[TestInstantiationAwareBeanPostProcessor]...postProcessBeforeInstantiation..." + beanName);
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (isMatchClass(bean.getClass())) {
            System.out.println("进入[TestInstantiationAwareBeanPostProcessor]...postProcessAfterInstantiation..." + beanName);
        }
        return true;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        if (isMatchClass(bean.getClass())) {
            System.out.println("进入[TestInstantiationAwareBeanPostProcessor]...postProcessPropertyValues..." + beanName);
        }
        return pvs;
    }

    //BeanPostProcessor的方法
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (isMatchClass(bean.getClass())) {
            System.out.println("进入[TestInstantiationAwareBeanPostProcessor]...postProcessBeforeInitialization..." + beanName);
        }
        return bean;
    }

    //BeanPostProcessor的方法
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (isMatchClass(bean.getClass())) {
            System.out.println("进入[TestInstantiationAwareBeanPostProcessor]...postProcessAfterInitialization..." + beanName);
        }
        return bean;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if (isMatchClass(bean.getClass())) {
            System.out.println("进入[TestInstantiationAwareBeanPostProcessor]...postProcessProperties..." + beanName);
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessProperties(pvs, bean, beanName);
    }

    private boolean isMatchClass(Class<?> beanClass) {
        return TestUser.class.equals(ClassUtils.getUserClass(beanClass));
    }
}