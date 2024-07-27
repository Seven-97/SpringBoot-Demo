package com.seven.controllervalidation.entity.vo;

import com.seven.controllervalidation.entity.pojo.Gender;
import com.seven.controllervalidation.enums.InEnum;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserVo {

    private Integer id;

    @NotBlank(message = "用户名不能为空！")
    @Size(max = 20, min = 1, message = "用户名长度不能超过20")
    private String userName;

    @NotBlank(message = "昵称不能为空！")
    @Size(max = 20, min = 1, message = "昵称长度不能超过20")
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

}
