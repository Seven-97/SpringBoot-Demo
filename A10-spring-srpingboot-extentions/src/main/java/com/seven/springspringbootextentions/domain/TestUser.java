package com.seven.springspringbootextentions.domain;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class TestUser implements InitializingBean
        , BeanNameAware
        , BeanClassLoaderAware
        , BeanFactoryAware
        , EnvironmentAware, EmbeddedValueResolverAware, ResourceLoaderAware, ApplicationEventPublisherAware, MessageSourceAware, ApplicationContextAware
        , DisposableBean {
    String name;
    String password;

    public TestUser() {
        System.out.println("TestUser无参构造器...创建【TestUser】对象");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("TestUser...setName...设置【name】属性");
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        System.out.println("TestUser...setPassword...设置【password】属性");
        this.password = password;
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("TestUser...[InitializingBean]...afterPropertiesSet...所有属性设置完毕");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("TestUser...[ApplicationContextAware]...setApplicationContext...【" + applicationContext + "】");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("TestUser...[BeanNameAware]...setBeanName...【" + s + "】");
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("TestUser...[BeanFactoryAware]...setBeanFactory...【" + beanFactory + "】");
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.out.println("TestUser...[ApplicationEventPublisherAware]...setApplicationEventPublisher...【" + applicationEventPublisher + "】");
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        System.out.println("TestUser...[EmbeddedValueResolverAware]...setEmbeddedValueResolver...【" + stringValueResolver + "】");
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("TestUser...[EnvironmentAware]...setEnvironment...【" + environment + "】");
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        System.out.println("TestUser...[MessageSourceAware]...setMessageSource...【" + messageSource + "】");
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println("TestUser...[ResourceLoaderAware]...setResourceLoader...【" + resourceLoader + "】");
    }

    @PostConstruct
    public void initPostConstruct() {
        System.out.println("TestUser...[PostConstruct]...initPostConstruct");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("TestUser...[BeanClassLoaderAware]...setBeanClassLoader【" + classLoader + "】");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("TestUser...[DisposableBean]...destroy");
    }

    @PreDestroy
    public void destroyPreDestroy() {
        System.out.println("TestUser...[PreDestroy]...destroyPreDestroy");
    }


}
