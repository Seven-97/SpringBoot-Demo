package com.seven.controllervalidation.result;

import lombok.Getter;

/**
 * 用户管理相关结果
 */
@Getter
public enum UserResultConstants implements ResultConstats{

    NOT_ID("U1001","未正确输入用户id"),
    ADD_FAIL("U1002","新增用户失败"),
    UPDATE_FAIL("U1003","更新用户失败"),
    DELETE_FAIL("U1004","删除用户失败"),
    USERNAME_EXIST("U1005","用户名已存在"),
    USER_NOT_EXIST("U1006","用户不存在")
    ;


    private String code;
    private String message;

    private UserResultConstants(String code, String message){
        this.code = code;
        this.message = message;
    }

}
