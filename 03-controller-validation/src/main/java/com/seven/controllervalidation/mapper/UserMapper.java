package com.seven.controllervalidation.mapper;


import com.seven.controllervalidation.entity.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * user的dao层
 */
@Repository
public interface UserMapper {

    /**
     * 查询所有用户信息
     *
     * @return 返回所有用户信息
     */
    List<User> selectUserList();

    /**
     * 根据用户id查询用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    User selectUserById(Integer id);

    /**
     * 根据用户名查询用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    User selectUserByUserName(String userName);

    /**
     * 新增用户数据
     *
     * @param userVo 具体的用户数据
     * @return 返回0或1;0表示插入失败,1表示插入成功
     */
    int insertUser(User userVo);

    /**
     * 更新用户数据
     *
     * @param userVo 具体的用户数据
     * @return 返回0或1;0表示更新失败,1表示更新成功
     */
    int updateUser(User userVo);

    /**
     * 删除用户数据
     *
     * @param id 用户id
     * @return 返回0或1;0表示删除失败,1表示删除成功
     */
    int deleteUser(Integer id);

}
