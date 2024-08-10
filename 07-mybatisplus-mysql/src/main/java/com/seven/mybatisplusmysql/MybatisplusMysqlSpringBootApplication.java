package com.seven.mybatisplusmysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.seven.mybatisplusmysql")
public class MybatisplusMysqlSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisplusMysqlSpringBootApplication.class, args);
    }
}
