package com.seven.springspringbootextentions.extentions;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;

public class Test5SmartInstantiationAwareBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {

    //获得提前暴露的bean引用，主要用于Spring循环依赖问题的解决；
    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
        System.out.println("进入[Test5SmartInstantiationAwareBeanPostProcessor]...getEarlyBeanReference..." + bean + "..." + beanName);
        return bean;
    }

    //预测Bean的类型，返回第一个预测成功的Class类型，如果不能预测返回null；
    @Override
    public Class<?> predictBeanType(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("[TestSmartInstantiationAwareBeanPostProcessor]...predictBeanType..." + beanClass + "..." + beanName);
        return beanClass;
    }

    //决定使用哪个构造器构造Bean，如果不指定，默认为null，即bean的无参构造方法；
    @Override
    public Constructor<?>[] determineCandidateConstructors(Class<?> beanClass, String beanName) throws BeansException {
        return SmartInstantiationAwareBeanPostProcessor.super.determineCandidateConstructors(beanClass, beanName);
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return SmartInstantiationAwareBeanPostProcessor.super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return SmartInstantiationAwareBeanPostProcessor.super.postProcessAfterInstantiation(bean, beanName);
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        return SmartInstantiationAwareBeanPostProcessor.super.postProcessProperties(pvs, bean, beanName);
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        return SmartInstantiationAwareBeanPostProcessor.super.postProcessPropertyValues(pvs, pds, bean, beanName);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return SmartInstantiationAwareBeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }
}
