package com.seven.controllervalidation.entity.pojo;


import com.seven.controllervalidation.enums.InEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @Min(value = 1, message = "id输入不合法")
    private Integer id;

    @NotBlank(message = "用户名不能为空！")
    @Size(max = 20, min = 1, message = "用户名长度不能超过20")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "用户名只能包含字母和数字")
    private String userName;

    @NotBlank(message = "昵称不能为空！")
    @Size(max = 20, min = 1, message = "昵称长度不能超过20")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "昵称只能包含字母和数字")
    private String nickname;

    @NotBlank(message = "年龄不能为空！")
    @Min(value = 1, message = "年龄必须大于0")
    @Max(value = 200, message = "年龄不能超过200")
    private String age;

    @NotBlank(message = "性别不能为空！")
    @InEnum(enumType = Gender.class, message = "用户性别不在枚举范围内")
    private String gender;

    @Size(max = 200)
    private String comment;

    private Date updateTime;
    private Date createTime;
    private int deleteState;

}


