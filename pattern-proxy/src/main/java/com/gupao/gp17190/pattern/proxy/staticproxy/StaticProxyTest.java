package com.gupao.gp17190.pattern.proxy.staticproxy;

import com.gupao.gp17190.pattern.proxy.dynamic.ITraval;
import com.gupao.gp17190.pattern.proxy.dynamic.Travaler;

public class StaticProxyTest {
    public static void main(String[] args) {
        ITraval traval = new StaticTravalAgency(new Travaler());
        traval.traval();
    }
}
