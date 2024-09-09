package com.seven.controllervalidation.controller;


import com.seven.controllervalidation.entity.pojo.User;
import com.seven.controllervalidation.entity.vo.UserVo;
import com.seven.controllervalidation.exception.UserException;
import com.seven.controllervalidation.service.UserService;
import com.seven.controllervalidation.util.JsonMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户控制层，获取前端数据，执行数据校验，数据返回
 */
@RestController
@RequestMapping("/user")
@Slf4j
@Validated
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 查询所有用户信息
     *
     * @return 返回所有用户信息
     */
    @GetMapping(value = "/list")
    public List<UserVo> userList() {
        List<UserVo> users = userService.selectUserList();
        log.info("userList查询的用户信息为:{}", JsonMapper.toJson(users));
        return users;
    }

    /**
     * 根据用户id查询用户信息
     *
     * @param id 用户id
     * @return 成功则返回用户信息
     */
    @GetMapping("/selectById")
    public UserVo selectById(@RequestParam(required = false) Integer id) {
        UserVo user = userService.selectUserById(id);
        log.info("selectById根据id:{}执行查询,查询的用户信息为:{}", id, JsonMapper.toJson(user));
        if (user == null) {
            throw new UserException("查询的用户不存在");
        }
        return user;
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param userName 根据param获取用户名
     */
    @GetMapping("/selectByUserName")
    public UserVo selectByUserName(@RequestParam(required = false) @NotBlank(message = "userName不能为空") String userName) {
        UserVo user = userService.selectUserByUserName(userName);
        log.info("selectByUserName根据userName:{}执行查询,查询的用户信息为:{}", userName, JsonMapper.toJson(user));
        if (user == null) {
            throw new UserException("查询的用户不存在");
        }
        return user;
    }

    /**
     * 新增用户数据
     *
     * @param user 通过json格式接收具体的用户数据
     */
    @PostMapping("/insert")
    public void insertUser(@Validated @RequestBody User user) {

        //检查用户名是否已存在
        UserVo userQuery = userService.selectUserByUserName(user.getUserName());
        if (userQuery != null) {
            log.error("插入的用户名已存在");
            throw new UserException("插入的用户名已存在");
        }

        int res = userService.insertUser(user);
        log.info("insert的用户信息为:{},插入结果为(0表示失败,1表示成功):{}", JsonMapper.toJson(user), res);
        if (res == 0) {
            throw new UserException("插入失败");
        }
    }

    /**
     * 更新用户数据
     *
     * @param user 通过json格式接收具体的用户数据
     */
    @PostMapping("/update")
    public void updateUser(@Validated @RequestBody User user) {
        if (user.getId() == null) {
           throw new UserException("id不能为空");
        }

        //检查用户名是否已存在
        UserVo userQuery = userService.selectUserByUserName(user.getUserName());
        if (userQuery != null) {
            log.error("更新的用户名已存在");
            throw new UserException("更新的用户名已存在");
        }

        int res = userService.updateUser(user);
        log.info("update的用户信息为:{},更新结果为(0表示失败,1表示成功):{}", JsonMapper.toJson(user), res);
        if (res == 0) {
            throw new UserException("更新失败");
        }
    }

    /**
     * 删除用户数据
     *
     * @param id 用户id
     */
    @PostMapping("/delete")
    public void deleteUser(@RequestParam(required = false) @NotNull(message = "id不能为空") Integer id) {

        int res = userService.deleteUser(id);
        log.info("delete的用户id为:{},删除结果为(0表示失败,1表示成功):{}", id, res);
        if (res == 0) {
            throw new UserException("删除失败");
        }
    }

}
