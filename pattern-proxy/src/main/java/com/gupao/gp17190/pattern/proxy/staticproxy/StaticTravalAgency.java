package com.gupao.gp17190.pattern.proxy.staticproxy;

import com.gupao.gp17190.pattern.proxy.dynamic.ITraval;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class StaticTravalAgency implements ITraval {

    private ITraval target;

    public StaticTravalAgency(ITraval target) {
        this.target = target;
    }

    public void traval() {
        before();
        target.traval();
        after();
    }

    private void before() {
        System.out.println("告诉我你想去哪，我来安排。。。");
    }

    private void after() {
        System.out.println("旅行完美结束，记得给好评哦！！！");
    }

}
