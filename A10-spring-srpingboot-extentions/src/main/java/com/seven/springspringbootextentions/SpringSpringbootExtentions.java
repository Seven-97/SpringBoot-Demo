package com.seven.springspringbootextentions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 */
@SpringBootApplication
public class SpringSpringbootExtentions {
    public static void main(String[] args) {
//        SpringApplication springApplication = new SpringApplication(SpringSpringbootExtentions.class);
//        springApplication.addInitializers(new TestApplicationContextInitializer());
//        springApplication.run(args);
        System.out.println("SpringSpringbootExtentions开始启动");
        SpringApplication.run(SpringSpringbootExtentions.class);
        System.out.println("SpringSpringbootExtentions启动结束");
    }
}
