package com.seven.aoplimitrepeatsubmitantishake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class App {
    public static void main( String[] args ) {
        SpringApplication.run(App.class);
    }
}
