package com.gupao.netty.tomcat.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

/**
 * @Author laihui
 * @Date 2019/6/27
 * @Desc
 * @Version 1.0
 **/
public class MyResponse {
    private ChannelHandlerContext ctx;

    private HttpRequest httpRequest;

    public MyResponse(ChannelHandlerContext ctx, HttpRequest httpRequest) {
        this.ctx = ctx;
        this.httpRequest = httpRequest;
    }

    public void write(String content) throws Exception {
        if (content == null || content.length() == 0) return;
        try {
            FullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(content.getBytes("utf-8"))
            );
            response.headers().set("Content-Type", "text/html");
            ctx.write(response);
        }
        finally {
            this.ctx.flush();
            this.ctx.close();
        }
    }
}
