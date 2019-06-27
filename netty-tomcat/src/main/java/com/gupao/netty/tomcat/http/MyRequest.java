package com.gupao.netty.tomcat.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.List;
import java.util.Map;

/**
 * @Author laihui
 * @Date 2019/6/27
 * @Desc
 * @Version 1.0
 **/
public class MyRequest {
    private ChannelHandlerContext ctx;

    private HttpRequest httpRequest;

    public MyRequest(ChannelHandlerContext ctx, HttpRequest httpRequest) {
        this.ctx = ctx;
        this.httpRequest = httpRequest;
    }

    public String getMethod() {
        return this.httpRequest.method().name();
    }

    public String getUrl() {
        return this.httpRequest.uri();
    }

    public String getParameter(String name) {
        Map<String, List<String>> params = new QueryStringDecoder(getUrl()).parameters();
        if (!params.containsKey(name)) {
            return null;
        }
        List<String> values = params.get(name);
        return values.get(0);
    }
}
