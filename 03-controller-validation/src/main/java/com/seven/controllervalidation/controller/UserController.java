package com.seven.controllervalidation.controller;



import com.google.common.primitives.Ints;
import com.seven.controllervalidation.entity.pojo.User;
import com.seven.controllervalidation.entity.vo.UserVo;
import com.seven.controllervalidation.result.Result;
import com.seven.controllervalidation.result.UserResultConstants;
import com.seven.controllervalidation.service.UserService;
import com.seven.controllervalidation.utils.JsonMapper;
import jdk.internal.joptsimple.internal.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户控制层，获取前端数据，执行数据校验，数据返回
 */
@RestController
@RequestMapping("/user")
@Slf4j
@Validated
public class UserController {

    private final UserService userService;

    //构造器注入
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 查询所有用户信息
     *
     * @return 返回所有用户信息
     */
    @GetMapping(value = "/list")
    public Result userList() {
        List<UserVo> users = userService.selectUserList();
        log.info("userList查询的用户信息为:{}", JsonMapper.toJson(users));
        return Result.ok(users);
    }

    /**
     * 根据用户id查询用户信息
     *
     * @param id 用户id
     * @return 成功则返回用户信息，失败则返回error提示信息
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable String id) {
        Integer intId = Ints.tryParse(id);
        if (intId == null) {
            return Result.error(UserResultConstants.NOT_ID.getCode(), UserResultConstants.NOT_ID.getMessage());
        }

        UserVo user = userService.selectUserById(intId);
        log.info("selectById根据id:{}执行查询,查询的用户信息为:{}", id, JsonMapper.toJson(user));
        if (user != null) {
            return Result.ok(user);
        }
        return Result.ok();
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param userName 根据param获取用户名
     * @return 成功则返回用户信息，失败则返回error提示信息
     */
    @GetMapping("/selectByUserName/{userName}")
    public Result selectByUserName(@PathVariable String userName) {
        if (Strings.isNullOrEmpty(userName)) {
            return Result.error(UserResultConstants.USER_NOT_EXIST.getCode(), UserResultConstants.USER_NOT_EXIST.getMessage());
        }
        UserVo user = userService.selectUserByUserName(userName);
        log.info("selectByUserName根据userName:{}执行查询,查询的用户信息为:{}", userName, JsonMapper.toJson(user));
        if (user != null) {
            return Result.ok(user);
        }
        return Result.error(UserResultConstants.USER_NOT_EXIST.getCode(), UserResultConstants.USER_NOT_EXIST.getMessage());
    }

    /**
     * 新增用户数据
     *
     * @param user 通过json格式接收具体的用户数据
     * @return 成功则返回新增用户成功，失败则返回新增用户失败
     */
    @PostMapping("/insert")
    public Result insertUser(@Validated @RequestBody User user) {

        //检查用户名是否已存在
        UserVo userQuery = userService.selectUserByUserName(user.getUserName());
        if (userQuery != null) {
            log.error("插入的用户名已存在");
            return Result.error(UserResultConstants.USERNAME_EXIST.getCode(), UserResultConstants.USERNAME_EXIST.getMessage());
        }

        int res = userService.insertUser(user);
        log.info("insert的用户信息为:{},插入结果为(0表示失败,1表示成功):{}", JsonMapper.toJson(user), res);
        if (res == 0) {
            return Result.error(UserResultConstants.ADD_FAIL.getCode(), UserResultConstants.ADD_FAIL.getMessage());
        }

        return Result.ok(UserResultConstants.ADD_SUCCESS.getCode(), UserResultConstants.ADD_SUCCESS.getMessage());
    }

    /**
     * 更新用户数据
     *
     * @param user 通过json格式接收具体的用户数据
     * @return 成功则返回更新用户成功，失败则返回更新用户失败
     */
    @PostMapping("/update")
    public Result updateUser(@Validated @RequestBody User user) {
        if (user.getId() == null) {
            return Result.error(UserResultConstants.NOT_ID.getCode(), UserResultConstants.NOT_ID.getMessage());
        }

        //检查用户名是否已存在
        UserVo userQuery = userService.selectUserByUserName(user.getUserName());
        if (userQuery != null) {
            log.error("更新的用户名已存在");
            return Result.error(UserResultConstants.USERNAME_EXIST.getCode(), UserResultConstants.USERNAME_EXIST.getMessage());
        }

        int res = userService.updateUser(user);
        log.info("update的用户信息为:{},更新结果为(0表示失败,1表示成功):{}", JsonMapper.toJson(user), res);
        if (res == 0) {
            return Result.error(UserResultConstants.UPDATE_FAIL.getCode(), UserResultConstants.UPDATE_FAIL.getMessage());
        }
        return Result.ok(UserResultConstants.UPDATE_SUCCESS.getCode(), UserResultConstants.UPDATE_SUCCESS.getMessage());
    }

    /**
     * 删除用户数据
     *
     * @param id 用户id
     * @return 成功则返回删除用户成功，失败则返回删除用户失败
     */
    @PostMapping("/delete/{id}")
    public Result deleteUser(@PathVariable String id) {

        Integer intId = Ints.tryParse(id);
        if (intId == null) {
            return Result.error(UserResultConstants.NOT_ID.getCode(), UserResultConstants.NOT_ID.getMessage());
        }
        int res = userService.deleteUser(intId);
        log.info("delete的用户id为:{},删除结果为(0表示失败,1表示成功):{}", id, res);
        if (res == 0) {
            return Result.error(UserResultConstants.DELETE_FAIL.getCode(), UserResultConstants.DELETE_FAIL.getMessage());
        }
        return Result.ok(UserResultConstants.DELETE_SUCCESS.getCode(), UserResultConstants.DELETE_SUCCESS.getMessage());
    }

}
