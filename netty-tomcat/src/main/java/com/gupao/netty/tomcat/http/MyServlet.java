package com.gupao.netty.tomcat.http;

/**
 * @Author laihui
 * @Date 2019/6/27
 * @Desc
 * @Version 1.0
 **/
public abstract class MyServlet {

    public void service(MyRequest req, MyResponse resp) throws Exception {
        if ("GET".equalsIgnoreCase(req.getMethod())) {
            doGet(req, resp);
        }
        else {
            doPost(req, resp);
        }
    }

    protected abstract void doGet(MyRequest req, MyResponse resp) throws Exception;

    protected abstract void doPost(MyRequest req, MyResponse resp) throws Exception;
}
