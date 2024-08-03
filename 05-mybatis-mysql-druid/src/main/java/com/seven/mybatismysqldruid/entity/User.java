package com.seven.mybatismysqldruid.entity;

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

    private Integer id;

    private String name;

    private Integer age;

    private Gender gender;
}
