package com.seven.controllervalidation.mapper;


import com.seven.controllervalidation.entity.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * user的dao层,主要演示注解校验，这里不做具体实现
 */
@Repository
public class UserMapperImpl {

    /**
     * 查询所有用户信息
     *
     * @return 返回所有用户信息
     */
    public List<User> selectUserList(){
        return null;
    }

    /**
     * 根据用户id查询用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    public User selectUserById(Integer id){
        return null;
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    public User selectUserByUserName(String userName){
        return null;
    }

    /**
     * 新增用户数据
     *
     * @param userVo 具体的用户数据
     * @return 返回0或1;0表示插入失败,1表示插入成功
     */
    public int insertUser(User userVo){
        return 0;
    }

    /**
     * 更新用户数据
     *
     * @param userVo 具体的用户数据
     * @return 返回0或1;0表示更新失败,1表示更新成功
     */
    public int updateUser(User userVo){
        return 0;
    }

    /**
     * 删除用户数据
     *
     * @param id 用户id
     * @return 返回0或1;0表示删除失败,1表示删除成功
     */
    public int deleteUser(Integer id){
        return 0;
    }

}
