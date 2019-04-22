package com.gupao.gp17190.springframework.web.servlet;

import com.gupao.gp17190.springframework.context.support.MyApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * @Author laihui
 * @Date 2019/4/19
 * @Desc
 * @Version 1.0
 **/
public abstract class MyFrameworkServlet extends HttpServlet {
    private static final String CONTEXT_CONFIG_LOCATION = "contextConfigLocation";

    private MyApplicationContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        context = new MyApplicationContext(config.getInitParameter(CONTEXT_CONFIG_LOCATION));
        onRefresh(context);
    }

    protected void onRefresh(MyApplicationContext context) {
    }
}
