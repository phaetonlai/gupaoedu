package com.gupaoedu.concurrent.aqs;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author laihui
 * @version 1.0
 * @date 2019/5/19
 * @description
 **/
public class LockTest {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            // TODO
        } finally {
            lock.unlock();
        }

        ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
        Lock readLock = rwl.readLock();
        readLock.lock();
        readLock.unlock();

        Lock writeLock = rwl.writeLock();
        writeLock.lock();
        writeLock.unlock();

    }
}
