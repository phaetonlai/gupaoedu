package com.gupao.sentinel.dubbo.controller;

import com.gupao.dubbo.rpc.service.api.ILoginService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author laihui
 * @Date 2019/8/12
 * @Desc
 * @Version 1.0
 **/
@RestController
@RequestMapping("/")
public class SentinelController {
    @Reference
    private ILoginService loginService;

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name) {
        return loginService.login(name);
    }
}
