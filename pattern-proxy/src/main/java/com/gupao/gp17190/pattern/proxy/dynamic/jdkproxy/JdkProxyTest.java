package com.gupao.gp17190.pattern.proxy.dynamic.jdkproxy;

import com.gupao.gp17190.pattern.proxy.dynamic.ITraval;
import com.gupao.gp17190.pattern.proxy.dynamic.Travaler;

public class JdkProxyTest {
    public static void main(String[] args) {
        ITraval traval = (ITraval) new JdkTravalAgency().getInstance(new Travaler());
        System.out.println(traval);
        traval.traval();
    }
}
