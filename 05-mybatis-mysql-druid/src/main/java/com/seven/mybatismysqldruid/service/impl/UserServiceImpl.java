package com.seven.mybatismysqldruid.service.impl;

import com.seven.mybatismysqldruid.entity.User;
import com.seven.mybatismysqldruid.mapper.UserMapper;
import com.seven.mybatismysqldruid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Seven
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectAll() {
        return userMapper.slectAll();
    }
}

