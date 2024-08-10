package com.seven.mybatisplusmysql.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.seven.mybatisplusmysql.domain.dto.PageDTO;
import com.seven.mybatisplusmysql.domain.dto.UserFormDTO;
import com.seven.mybatisplusmysql.domain.po.User;
import com.seven.mybatisplusmysql.domain.query.UserQuery;
import com.seven.mybatisplusmysql.domain.vo.UserVO;
import com.seven.mybatisplusmysql.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Seven
 * @since 2024-08-10
 */
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/user")
//RequiredArgsConstructor是Lombok 注解，自动生成所有 final 字段的构造函数，Spring推荐构造器注入，详情见 https://www.seven97.top/framework/spring/ioc1-summary.html
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping
    @ApiOperation("新增用户")
    public void saveUser(@RequestBody UserFormDTO userFormDTO){
        // 1.转换DTO为PO
        User user = BeanUtil.copyProperties(userFormDTO, User.class);
        // 2.新增
        userService.save(user);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除用户")
    public void removeUserById(@PathVariable("id") Long userId){
        userService.removeById(userId);
    }

//    @GetMapping("/{id}")
//    @ApiOperation("根据id查询用户")
//    public UserVO queryUserById(@PathVariable("id") Long userId){
//        // 1.查询用户
//        User user = userService.getById(userId);
//        // 2.处理vo
//        return BeanUtil.copyProperties(user, UserVO.class);
//    }

    /**
     * 改造根据id用户查询的接口，查询用户的同时返回用户收货地址列表
     * @param userId
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询用户")
    public UserVO queryUserById(@PathVariable("id") Long userId){
        // 基于自定义service方法查询
        return userService.queryUserAndAddressById(userId);
    }


    @GetMapping
    @ApiOperation("根据id集合查询用户")
    public List<UserVO> queryUserByIds(@RequestParam("ids") List<Long> ids){
        // 1.查询用户
        List<User> users = userService.listByIds(ids);
        // 2.处理vo
        return BeanUtil.copyToList(users, UserVO.class);
    }

    @PutMapping("{id}/deduction/{money}")
    @ApiOperation("扣减用户余额")
    public void deductBalance(@PathVariable("id") Long id, @PathVariable("money")Integer money){
        userService.deductBalance(id, money);
    }

    /**
     * 实现一个根据复杂条件查询用户的接口，查询条件如下：
     * - name：用户名关键字，可以为空
     * - status：用户状态，可以为空
     * - minBalance：最小余额，可以为空
     * - maxBalance：最大余额，可以为空
     * 可以理解成一个用户的后台管理界面，管理员可以自己选择条件来筛选用户，因此上述条件不一定存在，需要做判断。
     */
    @GetMapping("/list")
    @ApiOperation("复杂条件查询用户")
    public List<UserVO> queryUsers(UserQuery query){
        // 1.组织条件
        String username = query.getName();
        Integer status = query.getStatus();
        Integer minBalance = query.getMinBalance();
        Integer maxBalance = query.getMaxBalance();

        // 方式一：
//        //username != null ，意思就是当条件成立时才会添加这个查询条件，类似Mybatis的mapper.xml文件中的<if>标签。这样就实现了动态查询条件效果了。
//        LambdaQueryWrapper<User> wrapper = new QueryWrapper<User>().lambda()
//                .like(username != null, User::getUsername, username)
//                .eq(status != null, User::getStatus, status)
//                .ge(minBalance != null, User::getBalance, minBalance)
//                .le(maxBalance != null, User::getBalance, maxBalance);
//        // 2.查询用户
//        List<User> users = userService.list(wrapper);

        // 方式二：无需自己通过new的方式来创建Wrapper，可以直接调用lambdaQuery和lambdaUpdate方法
        // 2.查询用户
        List<User> users = userService.lambdaQuery()
                .like(username != null, User::getUsername, username)
                .eq(status != null, User::getStatus, status)
                .ge(minBalance != null, User::getBalance, minBalance)
                .le(maxBalance != null, User::getBalance, maxBalance)
                .list();

        // 3.处理vo
        return BeanUtil.copyToList(users, UserVO.class);
    }

    @GetMapping("/page")
    @ApiOperation("分页查询")
    public PageDTO<UserVO> queryUsersPage(UserQuery query){
        return userService.queryUsersPage(query);
    }

}
