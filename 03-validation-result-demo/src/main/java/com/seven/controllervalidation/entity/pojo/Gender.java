package com.seven.controllervalidation.entity.pojo;

import com.seven.controllervalidation.enums.BasicEnum;

public enum Gender implements BasicEnum {
    MALE("1", "男性"),
    FEMALE("2", "女性"),
    SECRET("0", "保密");


    /**
     * 性别编号，请求参数使用
     */
    public final String SEX_CODE;
    /**
     * 性别名称，提示文案使用
     */
    public final String NAME;

    Gender(String sexCode, String name) {
        this.SEX_CODE = sexCode;
        this.NAME = name;
    }


    @Override
    public String getEnumCode() {
        return SEX_CODE;
    }

    @Override
    public String getEnumDesc() {
        return null;
    }
}
