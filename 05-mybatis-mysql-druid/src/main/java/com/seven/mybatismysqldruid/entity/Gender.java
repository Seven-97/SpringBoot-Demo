package com.seven.mybatismysqldruid.entity;

import lombok.Getter;

/**
 * @author Seven
 */
@Getter
public enum Gender {
    MALE(1, "男性"),
    FEMALE(2, "女性"),
    SECRECT(0, "保密");

    public int CODE;
    /**
     * 性别名称，提示文案使用
     */
    public String NAME;

    private Gender(int code, String name) {
        this.NAME = name;
        this.CODE = code;
    }

    public static Gender stateOf(int code) {
        for (Gender gender : values()) {
            if (gender.getCODE() == code) {
                return gender;
            }
        }
        return null;
    }

}
