package com.gupao.dubbo.rpc.service.provider.service;

import com.gupao.dubbo.rpc.service.api.ILoginService;

/**
 * @Author laihui
 * @Date 2019/7/25
 * @Desc
 * @Version 1.0
 **/
public class LoginServiceImpl implements ILoginService {
    public String login(String user) {
        return ("Login success, user : " + user);
    }
}
