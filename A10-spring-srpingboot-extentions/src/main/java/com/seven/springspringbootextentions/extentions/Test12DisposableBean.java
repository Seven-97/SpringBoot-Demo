package com.seven.springspringbootextentions.extentions;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

@Component
public class Test12DisposableBean implements DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("进入[Test12DisposableBean]...destroy...");
    }
}
