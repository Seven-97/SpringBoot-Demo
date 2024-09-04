package com.seven.springspringbootextentions.extentions;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class Test1ApplicationContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("进入[TestApplicationContextInitializer]...initialize..." + applicationContext);
    }
}
