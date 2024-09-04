package com.seven.springspringbootextentions.domain;

import org.springframework.beans.factory.InitializingBean;


public class User implements InitializingBean {
    String name;
    String password;

    public User() {
        System.out.println("User无参构造器...创建【User】对象");
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        System.out.println("User...setName...设置【name】属性");
        this.name = name;
    }

    public void setPassword(String password) {
        System.out.println("User...setPassword...设置【password】属性");
        this.password = password;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("User...所有属性设置完毕");
    }
}

