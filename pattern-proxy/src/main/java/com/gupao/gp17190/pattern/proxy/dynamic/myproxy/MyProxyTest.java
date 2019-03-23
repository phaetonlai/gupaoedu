package com.gupao.gp17190.pattern.proxy.dynamic.myproxy;

import com.gupao.gp17190.pattern.proxy.dynamic.ITraval;
import com.gupao.gp17190.pattern.proxy.dynamic.Travaler;

public class MyProxyTest {
    public static void main(String[] args) {
        ITraval traval = (ITraval) new MyTravalAgency().getInstance(new Travaler());
        System.out.println(traval);
        traval.traval();
    }

    public static interface MyInterface {
        Object genObject(String name, int num);
    }
}
