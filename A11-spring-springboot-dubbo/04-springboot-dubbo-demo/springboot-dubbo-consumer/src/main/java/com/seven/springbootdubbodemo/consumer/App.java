package com.seven.springbootdubbodemo.consumer;

import com.seven.springbootdubbodemo.api.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class App{

    @Reference
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public ApplicationRunner getBean(){
        return args -> {
            System.out.println(userService.getUser(1L));
        };
    }
}
