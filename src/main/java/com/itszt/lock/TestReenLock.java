
package com.itszt.lock;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "lock")
public class TestReenLock {

    public static void main(String[] args) throws InterruptedException {

        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                log.debug("t1获取锁,正在执行任务--------------");
                try {
                    TimeUnit.SECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlock();
                log.debug("t1释放锁,任务执行完成--------------");

            }

        });
        TimeUnit.SECONDS.sleep(1);
        Thread t2 = new Thread(() -> {
            lock.lock();
            try {
                try {
                    TimeUnit.SECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("t2获取锁,正在执行任务--------------");
            } finally {
                lock.unlock();
                log.debug("t2释放锁,任务执行完成--------------");
            }

        });
        TimeUnit.SECONDS.sleep(1);
        Thread t3 = new Thread(() -> {
            lock.lock();
            try {
                try {
                    TimeUnit.SECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("t2获取锁,正在执行任务--------------");
            } finally {
                lock.unlock();
                log.debug("t2释放锁,任务执行完成--------------");
            }

        });
        t1.start();
        t2.start();
        t3.start();
    }
}
