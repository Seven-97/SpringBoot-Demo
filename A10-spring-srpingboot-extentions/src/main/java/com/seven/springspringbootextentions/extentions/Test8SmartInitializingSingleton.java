package com.seven.springspringbootextentions.extentions;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;

@Component
public class Test8SmartInitializingSingleton implements SmartInitializingSingleton {
    @Override
    public void afterSingletonsInstantiated() {
        System.out.println("进入[Test8SmartInitializingSingleton]...afterSingletonsInstantiated...");
    }
}
