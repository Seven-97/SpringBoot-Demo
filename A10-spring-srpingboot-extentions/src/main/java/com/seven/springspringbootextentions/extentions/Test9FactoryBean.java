package com.seven.springspringbootextentions.extentions;


import com.seven.springspringbootextentions.domain.SubBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class Test9FactoryBean implements FactoryBean<SubBean> {

    public Test9FactoryBean() {
        System.out.println("Test9FactoryBean无参数构造方法...创建了Test9FactoryBean对象");
    }

    @Override
    public SubBean getObject() {
        System.out.println("Test9FactoryBean...getObject...");
        return new SubBean();
    }

    @Override
    public Class<?> getObjectType() {
        return SubBean.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
