package com.seven.mybatismysqldruid.service;

import com.seven.mybatismysqldruid.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Seven
 */

public interface UserService {
    List<User> selectAll();
}
