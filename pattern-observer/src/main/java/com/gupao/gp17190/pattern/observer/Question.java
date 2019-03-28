package com.gupao.gp17190.pattern.observer;

/**
 * @author laihui
 * @version 1.0
 * @date 2019/3/28
 * @description
 **/
public class Question {
    // 提问渠道
    private String channel;

    // 提问者用户名
    private String username;

    // 提问内容
    private String content;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
