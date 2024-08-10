package com.seven.mybatisplusmysql.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.seven.mybatisplusmysql.domain.dto.PageDTO;
import com.seven.mybatisplusmysql.domain.po.Address;
import com.seven.mybatisplusmysql.domain.po.User;
import com.seven.mybatisplusmysql.domain.query.PageQuery;
import com.seven.mybatisplusmysql.domain.query.UserQuery;
import com.seven.mybatisplusmysql.domain.vo.AddressVO;
import com.seven.mybatisplusmysql.domain.vo.UserVO;
import com.seven.mybatisplusmysql.enums.UserStatus;
import com.seven.mybatisplusmysql.mapper.UserMapper;
import com.seven.mybatisplusmysql.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Seven
 * @since 2024-08-10
 */
@Service
@Primary
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    // 根据id扣减余额 方式一
//    @Override
//    public void deductBalance(Long id, Integer money) {
//        // 1.查询用户
//        User user = getById(id);
//        // 2.判断用户状态
//        if (user == null || user.getStatus() == 2) {
//            throw new RuntimeException("用户状态异常");
//        }
//        // 3.判断用户余额
//        if (user.getBalance() < money) {
//            throw new RuntimeException("用户余额不足");
//        }
//        // 4.扣减余额
//        baseMapper.deductMoneyById(id, money);
//    }

    /** 根据id扣减余额 方式二
     * 改造根据id修改用户余额的接口
     * 要求为：如果扣减后余额为0，则将用户status修改为冻结状态（2）
     * @param id
     * @param money
     */
    @Override
    @Transactional
    public void deductBalance(Long id, Integer money) {
        // 1.查询用户
        User user = getById(id);
        // 2.校验用户状态
        if (user == null || user.getStatus() == UserStatus.FREEZE) {
            throw new RuntimeException("用户状态异常！");
        }
        // 3.校验余额是否充足
        if (user.getBalance() < money) {
            throw new RuntimeException("用户余额不足！");
        }
        // 4.扣减余额 update tb_user set balance = balance - ?
        int remainBalance = user.getBalance() - money;
        lambdaUpdate()
                .set(User::getBalance, remainBalance) // 更新余额
                .set(remainBalance == 0, User::getStatus, 2) // 动态判断，是否更新status
                .eq(User::getId, id)
                .eq(User::getBalance, user.getBalance()) // 乐观锁
                .update();
    }

    @Override
    public UserVO queryUserAndAddressById(Long userId) {
        // 1.查询用户
        User user = getById(userId);
        if (user == null) {
            return null;
        }
        // 2.查询收货地址
        //在查询地址时，采用了Db的静态方法，避免了注入AddressService，减少了循环依赖的风险。
        List<Address> addresses = Db.lambdaQuery(Address.class)
                .eq(Address::getUserId, userId)
                .list();
        // 3.处理vo
        UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);
        userVO.setAddresses(BeanUtil.copyToList(addresses, AddressVO.class));
        return userVO;
    }

    @Override
    public PageDTO<UserVO> queryUsersPage(UserQuery query) {
        // 1.构建条件
        Page<User> page = query.toMpPageDefaultSortByCreateTimeDesc();
        // 2.查询
        page(page);
        // 3.封装返回
        return PageDTO.of(page, UserVO.class);
    }


}
