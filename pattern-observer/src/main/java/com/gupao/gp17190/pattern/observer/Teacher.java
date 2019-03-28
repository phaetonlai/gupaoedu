package com.gupao.gp17190.pattern.observer;

import com.google.common.eventbus.Subscribe;

/**
 * @author laihui
 * @version 1.0
 * @date 2019/3/28
 * @description
 **/
public class Teacher {
    private String name;

    public Teacher(String name) {
        this.name = name;
    }

    // 接收消息
    @Subscribe
    public void receiveQuestion(Question question) {
        System.out.println("============================================================");
        System.out.println(name + "老师，你好！\n" +
                "您收到了一个来自“" + question.getChannel() + "”的提问，希望您解答，问题内容如下：\n" +
                question.getContent() + "\n" +
                "提问者：" + question.getUsername());
    }
}
