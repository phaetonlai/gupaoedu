package com.gupao.netty.tomcat.servlet;

import com.gupao.netty.tomcat.http.MyRequest;
import com.gupao.netty.tomcat.http.MyResponse;
import com.gupao.netty.tomcat.http.MyServlet;

/**
 * @Author laihui
 * @Date 2019/6/27
 * @Desc
 * @Version 1.0
 **/
public class HelloServlet extends MyServlet {
    protected void doGet(MyRequest req, MyResponse resp) throws Exception {
        resp.write("this is hello servlet!");
    }

    protected void doPost(MyRequest req, MyResponse resp) throws Exception {
        doGet(req, resp);
    }
}
