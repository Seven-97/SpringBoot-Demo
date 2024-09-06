package com.seven.springspringbootextentions;

import com.seven.springspringbootextentions.domain.Teacher;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class test {

    @Test
    public void test3(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.seven.springspringbootextentions");
        Teacher student = context.getBean(Teacher.class);
        System.out.println(("student的实际ClassName是----："+student.getClass().getName()));
    }
}
