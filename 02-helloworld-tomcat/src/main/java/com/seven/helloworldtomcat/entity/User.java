package com.seven.helloworldtomcat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author Seven
 */
@Data
@AllArgsConstructor
@ToString
public class User {

    private String name;

    private int age;
}
