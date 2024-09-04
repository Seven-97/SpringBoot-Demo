package com.seven.springspringbootextentions.extentions;

import com.seven.springspringbootextentions.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

@Component
public class Test2BeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        System.out.println("进入[TestBeanDefinitionRegistryPostProcessor]...postProcessBeanDefinitionRegistry..." + beanDefinitionRegistry);
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(User.class);
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        PropertyValue propertyValue1 = new PropertyValue("name", "Seven");
        PropertyValue propertyValue2 = new PropertyValue("password", "123456");
        propertyValues.addPropertyValue(propertyValue1);
        propertyValues.addPropertyValue(propertyValue2);
        beanDefinition.setPropertyValues(propertyValues);
        beanDefinitionRegistry.registerBeanDefinition("user", beanDefinition);
    }

    //BeanFactoryPostProcessor的方法
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("进入[TestBeanDefinitionRegistryPostProcessor]...postProcessBeanFactory..." + beanFactory);

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("user");
        System.out.println("打印[TestBeanDefinitionRegistryPostProcessor]...postProcessBeanFactory..." + beanDefinition.getBeanClassName());
        User user = beanFactory.getBean(User.class);
        System.out.println("打印[TestBeanDefinitionRegistryPostProcessor]...postProcessBeanFactory..." + user.getName());
        System.out.println("打印[TestBeanDefinitionRegistryPostProcessor]...postProcessBeanFactory..." + user.getPassword());
    }
}
