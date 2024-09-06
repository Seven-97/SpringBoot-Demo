package com.seven.springspringbootextentions.extentions;

import com.seven.springspringbootextentions.util.MatchClassUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class Test6BeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (MatchClassUtils.isMatchClass(bean.getClass())) {
            System.out.println("进入[Test6BeanPostProcessor]...postProcessBeforeInitialization..." + beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (MatchClassUtils.isMatchClass(bean.getClass())) {
            System.out.println("进入[Test6BeanPostProcessor]...postProcessAfterInitialization..." + beanName);
        }
        return bean;
    }
}
