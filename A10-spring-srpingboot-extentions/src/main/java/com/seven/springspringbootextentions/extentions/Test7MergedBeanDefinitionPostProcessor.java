package com.seven.springspringbootextentions.extentions;

import com.seven.springspringbootextentions.util.MatchClassUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

@Component
public class Test7MergedBeanDefinitionPostProcessor implements MergedBeanDefinitionPostProcessor {

    @Override
    public void postProcessMergedBeanDefinition(RootBeanDefinition rootBeanDefinition, Class<?> aClass, String s) {
        if (MatchClassUtils.isMatchClass(aClass)) {
            System.out.println("进入[Test7MergedBeanDefinitionPostProcessor]...postProcessMergedBeanDefinition..." + aClass);
        }
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (MatchClassUtils.isMatchClass(bean.getClass())) {
            System.out.println("进入[Test7MergedBeanDefinitionPostProcessor]...postProcessBeforeInitialization..." + beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (MatchClassUtils.isMatchClass(bean.getClass())) {
            System.out.println("进入[Test7MergedBeanDefinitionPostProcessor]...postProcessAfterInitialization..." + beanName);
        }
        return bean;
    }
}
