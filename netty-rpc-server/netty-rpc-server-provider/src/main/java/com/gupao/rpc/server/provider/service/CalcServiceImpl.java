package com.gupao.rpc.server.provider.service;

import com.gupao.rpc.server.api.ICalcService;
import com.gupao.rpc.server.provider.RpcService;

/**
 * @Author laihui
 * @Date 2019/6/20
 * @Desc
 * @Version 1.0
 **/
@RpcService(value = ICalcService.class)
public class CalcServiceImpl implements ICalcService {
    public void add(int a, int b) {
        System.out.println("a + b = " + (a + b));
    }

    public int sub(int a, int b) {
        return (a - b);
    }

    public int mult(int a, int b) {
        return (a * b);
    }

    public int div(int a, int b) {
        return (a / b);
    }
}
