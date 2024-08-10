package com.seven.mybatisplusmysql.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.seven.mybatisplusmysql.MybatisplusMysqlSpringBootApplication;
import com.seven.mybatisplusmysql.domain.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Seven
 */
@SpringBootTest(classes = MybatisplusMysqlSpringBootApplication.class)
public class WrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testQueryWrapper() {
        // 1.构建查询条件 where name like "R%" AND balance >= 500
        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .select("id", "username", "info", "balance")
                .likeRight("username", "R")
                .ge("balance", 500);
        // 2.查询数据
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }


    @Test
    void testUpdateByQueryWrapper() {
        // 1.构建查询条件 where name = "Jack"
        QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("username", "Jack");
        // 2.更新数据，user中非null字段都会作为set语句
        User user = new User();
        user.setBalance(2000);
        userMapper.update(user, wrapper);
    }

    @Test
    void testUpdateWrapper() {
        List<Long> ids = List.of(1L, 2L, 4L);
        // 1.生成SQL
        UpdateWrapper<User> wrapper = new UpdateWrapper<User>()
                .setSql("balance = balance - 200") // SET balance = balance - 200
                .in("id", ids); // WHERE id in (1, 2, 4)

        // 2.更新，注意第一个参数可以给null，也就是不填更新字段和数据，
        // 而是基于UpdateWrapper中的setSQL来更新
        userMapper.update(null, wrapper);
    }


    @Test
    void testLambdaQueryWrapper() {
        // 1.构建条件 WHERE username LIKE "%o%" AND balance >= 1000
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .select(User::getId, User::getUsername, User::getInfo, User::getBalance)
                .like(User::getUsername, "o")
                .ge(User::getBalance, 1000);
        // 2.查询
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }
}
