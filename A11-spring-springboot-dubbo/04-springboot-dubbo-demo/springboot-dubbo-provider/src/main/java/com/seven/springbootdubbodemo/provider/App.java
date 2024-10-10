package com.seven.springbootdubbodemo.provider;

import com.seven.springbootdubbodemo.api.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 *
 */
@EnableDubbo
@SpringBootApplication
public class App{

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
