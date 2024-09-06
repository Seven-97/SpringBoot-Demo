package com.seven.springspringbootextentions.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class Student {
    private String name = "xiao ming";

    @Autowired
    private Teacher teacher;

    public Student() {
        System.out.println("Student无参数构造器...创建Student对象");
    }

    public Student(String name) {
        this.name = name;
        System.out.println("Student有参数构造器...创建Student对象...构造器参数name=" + name);
    }

    public Student(Teacher teacher) {
        this.teacher = teacher;
        System.out.println("Student有参数构造器...创建Student对象...构造器参数teacher=" + teacher);
    }

    public Student(String name, Teacher teacher) {
        this.name = name;
        this.teacher = teacher;
        System.out.println("Student有参数构造器...创建Student对象...构造器参数name=" + name + ",teacher=" + teacher);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        System.out.println("Student...setTeacher...设置【teacher】属性");
        this.teacher = teacher;
    }
}
