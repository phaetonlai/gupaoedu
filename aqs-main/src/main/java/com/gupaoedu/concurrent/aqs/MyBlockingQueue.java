package com.gupaoedu.concurrent.aqs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author laihui
 * @version 1.0
 * @date 2019/5/20
 * @description
 **/
public class MyBlockingQueue<T> {
    private final List<T> elemennts;

    private final int capacity;

    private final Lock lock = new ReentrantLock();

    private final Condition putCondition = lock.newCondition();

    private final Condition takeCondition = lock.newCondition();

    public MyBlockingQueue() {
        this(16);
    }

    public MyBlockingQueue(int capacity) {
        this.capacity = capacity;
        elemennts = new ArrayList<T>(capacity);
    }

    public boolean put(T el) {
        lock.lock();
        try {
            if (elemennts.size() >= capacity) {
                putCondition.await();
            }
            if(elemennts.add(el)) {
                takeCondition.signal();
                return true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return false;
    }

    public T take() {
        lock.lock();
        try {
            if (elemennts.size() <= 0) {
                takeCondition.await();
            }
            T result = elemennts.remove(0);
            putCondition.signal();

            return result;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }
}
