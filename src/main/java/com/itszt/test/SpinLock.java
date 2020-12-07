package com.itszt.test;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 实现自旋锁
 *
 * 自旋锁优点:
 * 自旋锁不会使线程状态发生切换
 */
public class SpinLock {


    private AtomicReference<Thread> cas = new AtomicReference<Thread>();

    public void lock() {
        Thread current = Thread.currentThread();

        //利用CAS

        while (!cas.compareAndSet(null, current)) {
            //Do nothing
        }
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        cas.compareAndSet(current, null);
    }

}
