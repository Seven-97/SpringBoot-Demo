package com.seven.controllervalidation.service.impl;


import com.seven.controllervalidation.entity.pojo.User;
import com.seven.controllervalidation.entity.vo.UserVo;
import com.seven.controllervalidation.mapper.UserMapperImpl;
import com.seven.controllervalidation.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapperImpl userMapperImpl;

    //构造器注入
    @Autowired
    public UserServiceImpl(UserMapperImpl userMapperImpl) {
        this.userMapperImpl = userMapperImpl;
    }

    @Override
    public List<UserVo> selectUserList() {
        List<UserVo> userVos = new ArrayList<>();
        List<User> users = userMapperImpl.selectUserList();
        for (User user : users) {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user, userVo);
            userVos.add(userVo);
        }
        return userVos;
    }

    @Override
    public UserVo selectUserById(Integer id) {
        UserVo userVo = null;
        User user = userMapperImpl.selectUserById(id);
        if (user != null) {
            userVo = new UserVo();
            BeanUtils.copyProperties(user, userVo);
        }
        return userVo;
    }

    @Override
    public UserVo selectUserByUserName(String userName) {
        UserVo userVo = null;
        User user = userMapperImpl.selectUserByUserName(userName);
        if (user != null) {
            userVo = new UserVo();
            BeanUtils.copyProperties(user, userVo);
        }
        return userVo;
    }


    @Override
    public int insertUser(User userVo) {
        return userMapperImpl.insertUser(userVo);
    }


    @Override
    public int updateUser(User userVo) {
        return userMapperImpl.updateUser(userVo);
    }

    @Override
    public int deleteUser(Integer id) {
        return userMapperImpl.deleteUser(id);
    }

}
