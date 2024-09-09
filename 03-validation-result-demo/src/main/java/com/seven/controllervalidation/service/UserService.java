package com.seven.controllervalidation.service;


import com.seven.controllervalidation.entity.pojo.User;
import com.seven.controllervalidation.entity.vo.UserVo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户服务层，处理用户相关逻辑
 */
public interface UserService {

    /**
     * 查询所有用户信息
     * @return 返回所有用户信息
     */
    List<UserVo> selectUserList();

    /**
     * 根据用户id查询用户信息
     * @param id 用户id
     * @return 用户信息
     */
    UserVo selectUserById(@NotNull(message = "id不能为空") Integer id);


    /**
     * 根据用户名查询用户信息
     * @param userName 用户名
     * @return 用户信息
     */
    UserVo selectUserByUserName(String userName);

    /**
     * 新增用户数据
     * @param userVo 具体的用户数据
     * @return 返回0或1;0表示插入失败,1表示插入成功
     */
    int insertUser(User userVo);

    /**
     * 更新用户数据
     * @param userVo 具体的用户数据
     * @return 返回0或1;0表示更新失败,1表示更新成功
     */
    int updateUser(User userVo);

    /**
     * 删除用户数据
     * @param id 用户id
     * @return 返回0或1;0表示删除失败,1表示删除成功
     */
    int deleteUser(Integer id);

}
