package com.seven.springdubbodemo.springdubboconsumer;

import com.seven.springdubbodemo.springdubboapi.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class SpringDubboConsumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("consumer.xml");
        UserService bean = context.getBean(UserService.class);
        System.out.println(bean.getUser(1L));
    }
}
