package com.gupao.gp17190.springframework.aop;

import com.gupao.gp17190.springframework.aop.support.MyAdvisedSupport;

/**
 * @Author laihui
 * @Date 2019/4/29
 * @Desc
 * @Version 1.0
 **/
public class MyCglibAopProxy implements MyAopProxy {
    private MyAdvisedSupport support;

    public MyCglibAopProxy(MyAdvisedSupport support) {
        this.support = support;
    }

    @Override
    public Object getProxy() {
        return null;
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return null;
    }
}
