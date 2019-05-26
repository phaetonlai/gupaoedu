package com.gupaoedu.concurrent.aqs;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author laihui
 * @version 1.0
 * @date 2019/5/19
 * @description
 **/
public class ConditionTest {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        condition.await();
        condition.signal();
    }
}
