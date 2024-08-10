package com.seven.mybatisplusmysql.mapper;

import com.seven.mybatisplusmysql.MybatisplusMysqlSpringBootApplication;
import com.seven.mybatisplusmysql.domain.po.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Seven
 */
@SpringBootTest(classes = MybatisplusMysqlSpringBootApplication.class)
public class AddressMapperTest {

    @Autowired
    private AddressMapper addressMapper;

    @Test
    void testDeleteByLogic() {
        // 删除方法与以前没有区别
        addressMapper.deleteById(59L);
    }

    @Test
    void testQuery() {
        List<Address> list = addressMapper.selectList(null);
        list.forEach(System.out::println);
    }

}
