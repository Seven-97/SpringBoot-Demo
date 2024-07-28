package com.seven.controllervalidation.result;

import com.seven.controllervalidation.util.JsonMapper;
import lombok.Data;

/**
 * 结果对象
 */
@Data
public class Result {

    private String retCode;
    private String retMsg;
    private Object data;

    public static final String SUCCESSCODE = "0000";
    public static final String SUCCESSMSG = "成功";
    public static final String ERROR_CODE = "2222";
    public static final String ERROR_MSG = "失败";

    public static final String COMMENT_CODE = "3333";
    public static final String RUNNING_ERROR_MSG = "运行出错，请联系管理员";

    private Result() {
        this(SUCCESSCODE, SUCCESSMSG, null);
    }

    private Result(Object data) {
        this(SUCCESSCODE, SUCCESSMSG, data);
    }

    private Result(String retCode, String retMsg, Object data) {
        this.retCode = retCode;
        this.retMsg = retMsg;
        this.data = data;
    }

    private Result(String retCode, String retMsg) {
        this(retCode, retMsg, null);
    }

    public static Result ok() {
        return new Result();
    }

    public static Result ok(Object data) {
        return new Result(data);
    }

    public static Result ok(String retCode, String retMsg) {
        return new Result(retCode, retMsg);
    }

    public static Result error(String retCode, String retMsg) {
        return new Result(retCode, retMsg);
    }

    public String toJsonString() {
        return JsonMapper.toJson(this);
    }

}
