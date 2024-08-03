package com.seven.mybatismysqldruid.controller;

import com.seven.mybatismysqldruid.entity.User;
import com.seven.mybatismysqldruid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Seven
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String list() {
        List<User> userList = userService.queryAll(0,10);
        for (User user : userList) {
            System.out.println(user);
        }
        return "ok";
    }

}
