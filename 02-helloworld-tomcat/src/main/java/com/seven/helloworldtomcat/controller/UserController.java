package com.seven.helloworldtomcat.controller;

import com.seven.helloworldtomcat.entity.User;
import com.seven.helloworldtomcat.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Seven
 */
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/user")
    public String list() {
        List<User> userList = userService.findUserList();
        for (User user : userList) {
            System.out.println(user);
        }
        return "ok";
    }

}
