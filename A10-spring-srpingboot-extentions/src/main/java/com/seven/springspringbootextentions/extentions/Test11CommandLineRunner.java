package com.seven.springspringbootextentions.extentions;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Test11CommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("进入[Test11CommandLineRunner]...run...");
    }
}
