package com.seven.springspringbootextentions.extentions;

import com.seven.springspringbootextentions.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class Test3BeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("进入[Test3BeanFactoryPostProcessor]...postProcessBeanFactory..." + beanFactory);

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("user");
        System.out.println("打印[Test3BeanFactoryPostProcessor]...postProcessBeanFactory..." + beanDefinition.getBeanClassName());
        User user = beanFactory.getBean(User.class);
        System.out.println("打印[Test3BeanFactoryPostProcessor]...postProcessBeanFactory..." + user.getName());
        System.out.println("打印[Test3BeanFactoryPostProcessor]...postProcessBeanFactory..." + user.getPassword());
    }
}
