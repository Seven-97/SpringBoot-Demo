package com.seven.mybatismysqldruid.mapper;

import com.seven.mybatismysqldruid.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Seven
 */
@Repository
public interface UserMapper {

    List<User> queryAll(@Param("offset") int offset, @Param("limit") int limit);

}
