package com.seven.springspringbootextentions.extentions;

import com.seven.springspringbootextentions.util.MatchClassUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;

@Component
public class Test5SmartInstantiationAwareBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {

    //预测Bean的类型，返回第一个预测成功的Class类型，如果不能预测返回null；很少使用
//    @Override
//    public Class<?> predictBeanType(Class<?> beanClass, String beanName) throws BeansException {
//        if (isMatchClass(beanClass)) {
//            System.out.println("进入[Test5SmartInstantiationAwareBeanPostProcessor]...predictBeanType..." + beanName);
//            return Student.class;
//        }
//        return beanClass;
//    }

    //获得提前暴露的bean引用，主要用于Spring循环依赖问题的解决；
    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
        if (MatchClassUtils.isMatchClass(bean.getClass())) {
            System.out.println("进入[Test5SmartInstantiationAwareBeanPostProcessor]...getEarlyBeanReference..." + beanName);
        }
        return bean;
    }

    //决定使用哪个构造器构造Bean，如果不指定，默认为null，即bean的无参构造方法；
    //student类里是有四个构造方法，这里可以选择实际是两个：
    //一个无参数的构造方法，另一个是形参数是Teacher类型的(因为有注入teacher类型的)
    @Override
    public Constructor<?>[] determineCandidateConstructors(Class<?> beanClass, String beanName) throws BeansException {
        if (MatchClassUtils.isMatchClass(beanClass)) {
            System.out.println("进入[Test5SmartInstantiationAwareBeanPostProcessor]...determineCandidateConstructors..." + beanName);
            Constructor<?> constructor = beanClass.getConstructors()[0];
            Constructor<?>[] constructors = {constructor};
            return constructors;
        }
        return null;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (MatchClassUtils.isMatchClass(beanClass)) {
            System.out.println("进入[Test5SmartInstantiationAwareBeanPostProcessor]...postProcessBeforeInstantiation..." + beanName);
            return null;
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (MatchClassUtils.isMatchClass(bean.getClass())) {
            System.out.println("进入[Test5SmartInstantiationAwareBeanPostProcessor]...postProcessAfterInstantiation..." + beanName);
        }
        return true;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if (MatchClassUtils.isMatchClass(bean.getClass())) {
            System.out.println("进入[Test5SmartInstantiationAwareBeanPostProcessor]...postProcessProperties..." + beanName);
        }
        return SmartInstantiationAwareBeanPostProcessor.super.postProcessProperties(pvs, bean, beanName);
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        if (MatchClassUtils.isMatchClass(bean.getClass())) {
            System.out.println("进入[Test5SmartInstantiationAwareBeanPostProcessor]...postProcessPropertyValues..." + beanName);
        }
        return pvs;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (MatchClassUtils.isMatchClass(bean.getClass())) {
            System.out.println("进入[Test5SmartInstantiationAwareBeanPostProcessor]...postProcessBeforeInitialization..." + beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (MatchClassUtils.isMatchClass(bean.getClass())) {
            System.out.println("进入[Test5SmartInstantiationAwareBeanPostProcessor]...postProcessAfterInitialization..." + beanName);
        }
        return bean;
    }


}
