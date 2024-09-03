package com.seven.aoplimitrepeatsubmitantishake.controller;


import com.seven.aoplimitrepeatsubmitantishake.accesslimit.AccessLimit;
import com.seven.aoplimitrepeatsubmitantishake.antishakelimit.AntiShake;
import com.seven.aoplimitrepeatsubmitantishake.constants.Result;
import com.seven.aoplimitrepeatsubmitantishake.repeatlimit.RepeatSubmit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


@RestController()
@RequestMapping("/user")
public class UserController {

    @GetMapping("/getUser")
    @AccessLimit(times = 10, timeUnit = TimeUnit.SECONDS, maxCount = 5, preKey = "getUser", msg = "服务请求达到最大限制，请求被拒绝！")
    public Result getUser() {
        return Result.success("成功访问");
    }

    @PostMapping("/saveUser")
    @RepeatSubmit(limitType = RepeatSubmit.Type.PARAM, lockTime = 5, timeUnit = TimeUnit.SECONDS, preKey = "saveUser", msg = "请求重复提交")
    public Result saveUser() {
        return Result.success("成功保存");
    }

    @PostMapping("/clickButton")
    @AntiShake(value = 1000, timeUnit = TimeUnit.MILLISECONDS, preKey = "clickButton")
    public Result clickButton() {
        return Result.success("成功点击按钮");
    }

}
