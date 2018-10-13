package com.hnu.scw.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 测试BeanPostProcess接口(该接口的作用是用于当有bean被初始化的时候进行调用)
 * @author Administrator
 * @create 2017-12-25 19:27
 * @desc
 **/
@Component
public class BeanPostImplTest  implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("bean开始进行初始化！！！");
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("bean初始化完了！！！");
        return o;
    }
}
