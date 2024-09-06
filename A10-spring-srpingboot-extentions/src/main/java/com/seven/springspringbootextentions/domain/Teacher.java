package com.seven.springspringbootextentions.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class Teacher {
    private String name = "li lao shi";

    @Autowired
    private Student student;

    public Teacher() {
        System.out.println("Teacher无参数构造器...创建Teacher对象");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        System.out.println("Teacher...setStudent...设置【student】属性");
        this.student = student;
    }
}
