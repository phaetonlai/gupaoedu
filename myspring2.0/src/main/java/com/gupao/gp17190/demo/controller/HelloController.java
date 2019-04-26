package com.gupao.gp17190.demo.controller;

import com.gupao.gp17190.demo.service.HelloService;
import com.gupao.gp17190.springframework.beans.annotation.*;
import com.gupao.gp17190.springframework.web.servlet.MyModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author laihui
 * @Date 2019/4/25
 * @Desc
 * @Version 1.0
 **/
@MyController
@MyRequestMapping("/hello/")
public class HelloController {
    @MyAutowired
    private HelloService helloService;

    @MyRequestMapping("/sayHi")
    public MyModelAndView hello(HttpServletRequest req, HttpServletResponse resp,
                                @MyRequestParam("name") String name) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("name", name);

        return new MyModelAndView(helloService.hello(), model);
    }
}
