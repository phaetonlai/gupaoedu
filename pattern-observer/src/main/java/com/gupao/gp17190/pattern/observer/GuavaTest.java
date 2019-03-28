package com.gupao.gp17190.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author laihui
 * @version 1.0
 * @date 2019/3/28
 * @description
 **/
public class GuavaTest {
    public static void main(String[] args) {
        Question question = new Question();
        question.setUsername("Jackson");
        question.setContent("spring的事务传播机制？");
        Teacher James = new Teacher("James");
        Teacher Tom = new Teacher("Tom");
        List<Teacher> teachers = new ArrayList<Teacher>();
        teachers.add(James);
        teachers.add(Tom);

        GPer gper = GPer.getInstance();
        gper.register(teachers);
        gper.publishQuestion(question);
    }
}
