package com.seven.mybatisplusmysql.enums;

/**
 * @author Seven
 */

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum UserStatus {
    NORMAL(1, "正常"),
    FREEZE(2, "冻结")
    ;

    @EnumValue //和数据库的交互用code
    private final int value;

    @JsonValue //和前端的交互用desc，返回给前端这个值，前端也需要返回这个值
    private final String desc;

    UserStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
