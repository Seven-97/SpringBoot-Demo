package com.seven.controllervalidation.result;

import lombok.Getter;

/**
 * 用户管理相关结果
 */
@Getter
public enum UserResultConstants {

    NOT_ID("1001","未正确输入用户id"),
    ADD_SUCCESS("1002","新增用户成功"),
    ADD_FAIL("1003","新增用户失败"),
    UPDATE_SUCCESS("1004","更新用户成功"),
    UPDATE_FAIL("1005","更新用户失败"),
    DELETE_SUCCESS("1006","删除用户成功"),
    DELETE_FAIL("1007","删除用户失败"),
    USERNAME_EXIST("1008","用户名已存在"),
    USER_NOT_EXIST("1009","用户不存在")
    ;


    private String code;
    private String message;

    private UserResultConstants(String code, String message){
        this.code = code;
        this.message = message;
    }

}
