package com.seven.springspringbootextentions.domain;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author chenghaoy.yu
 * @description
 * @email chenghaoy.yu@qunar.com
 * @Date 2024/9/4 14:16
 */
@Component
public class TestUser implements InitializingBean {
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
    public void afterPropertiesSet() throws Exception {
        System.out.println("TestUser...所有属性设置完毕");
    }
}
