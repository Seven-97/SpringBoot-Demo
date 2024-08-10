package com.seven.mybatisplusmysql.service;

import com.seven.mybatisplusmysql.domain.dto.PageDTO;
import com.seven.mybatisplusmysql.domain.po.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.seven.mybatisplusmysql.domain.query.UserQuery;
import com.seven.mybatisplusmysql.domain.vo.UserVO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Seven
 * @since 2024-08-10
 */
public interface IUserService extends IService<User> {

    void deductBalance(Long id, Integer money);

    UserVO queryUserAndAddressById(Long userId);

    PageDTO<UserVO> queryUsersPage(UserQuery query);
}
