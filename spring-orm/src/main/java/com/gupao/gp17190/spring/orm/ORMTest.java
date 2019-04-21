package com.gupao.gp17190.spring.orm;

import java.util.List;

/**
 * @author laihui
 * @version 1.0
 * @date 2019/4/21
 * @description
 **/
public class ORMTest {
    public static void main(String[] args) {
        UserEO user = new UserEO();
        user.setId(2);
        List<UserEO> users = DaoSupport.select(user);
        System.out.println(users);
    }
}
