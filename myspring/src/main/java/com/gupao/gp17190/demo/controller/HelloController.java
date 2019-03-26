package com.gupao.gp17190.demo.controller;

import com.gupao.gp17190.demo.service.IHelloService;
import com.gupao.gp17190.mvcframework.annotation.MyAutowired;
import com.gupao.gp17190.mvcframework.annotation.MyController;
import com.gupao.gp17190.mvcframework.annotation.MyRequestMapping;
import com.gupao.gp17190.mvcframework.annotation.MyRequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author laihui
 * @version 1.0
 * @date 2019/3/26
 * @description
 **/
@MyController
@MyRequestMapping("/")
public class HelloController {

    @MyAutowired
    private IHelloService service;

    @MyRequestMapping("/hello")
    public String hello(@MyRequestParam("name") String name) {
        return service.hello(name);
    }

    @MyRequestMapping("/add")
    public void add(@MyRequestParam("a") Integer a, @MyRequestParam("b") Integer b, HttpServletResponse resp) {
        try {
            resp.getWriter().write("a + b = " + (a + b));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
