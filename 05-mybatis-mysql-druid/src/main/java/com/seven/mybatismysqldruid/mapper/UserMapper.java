package com.seven.mybatismysqldruid.mapper;

import com.seven.mybatismysqldruid.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Seven
 */
@Repository
public interface UserMapper {

    List<User> slectAll();

}
