package com.gupaoedu.concurrent.aqs;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author laihui
 * @version 1.0
 * @date 2019/5/18
 * @description
 **/
public class App {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap map = new ConcurrentHashMap(16);
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
//        condition.await();
        condition.signal();

    }
}
