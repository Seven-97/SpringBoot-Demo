package com.seven.mybatisplusmysql.service.impl;

import com.seven.mybatisplusmysql.domain.po.Address;
import com.seven.mybatisplusmysql.mapper.AddressMapper;
import com.seven.mybatisplusmysql.service.IAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Seven
 * @since 2024-08-10
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

}
