package com.gupao.gp17190.pattern.observer;

import com.google.common.eventbus.EventBus;

import java.util.List;

/**
 * @author laihui
 * @version 1.0
 * @date 2019/3/28
 * @description
 **/
public class GPer {
    private String name = "咕泡社区";

    private EventBus eventBus;

    private static final GPer instance = new GPer();

    private GPer() {
        this.eventBus = new EventBus();
    }

    public static GPer getInstance() {
        return instance;
    }

    // 发布问题
    public void publishQuestion(Question question){
        question.setChannel(this.name);
        System.out.println(question.getUsername() + "在" + this.name + "上提交了一个问题。");
        this.eventBus.post(question);
    }

    // 注册老师信息
    public void register(List<Teacher> teachers) {
        for (Teacher teacher : teachers) {
            this.eventBus.register(teacher);
        }
    }
}
