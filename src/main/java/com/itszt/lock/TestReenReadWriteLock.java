package com.itszt.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁加锁流程
 */
@Slf4j(topic = "ReentrantReadWriteLock")
public class TestReenReadWriteLock {

    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    static ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    static ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    public static void main(String[] args) throws InterruptedException {


        Thread t1 = new Thread(() -> {
//            writeLock.lock();
            readLock.lock();

            try {
                log.debug("t1线程加读锁成功,正在执行任务");
                try {
                    TimeUnit.SECONDS.sleep(1000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                readLock.unlock();
//                writeLock.lock();
                log.debug("t1执行完成,释放锁");
            }
        }, "t1");

        TimeUnit.SECONDS.sleep(1);

        Thread t2 = new Thread(() -> {

            writeLock.lock();
            try {
                log.debug("t2线程加锁成功,正在执行任务");
                try {
                    TimeUnit.SECONDS.sleep(1000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                writeLock.unlock();
                log.debug("t2执行完成,释放锁");
            }

        }, "t2");

//        TimeUnit.SECONDS.sleep(1);
//
        Thread t3 = new Thread(() -> {
            readLock.lock();
            method1();
            try {
                log.debug("t3线程加写锁成功,正在执行任务");
            } finally {
                readLock.unlock();
                log.debug("t3执行完成,释放锁");
            }
        }, "t3");

//        Thread t4 = new Thread(() -> {
//            writeLock.lock();
//            try {
//                log.debug("t4线程加锁成功,正在执行任务");
//                try {
//                    TimeUnit.SECONDS.sleep(1000000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            } finally {
//                writeLock.unlock();
//                log.debug("t4执行完成,释放锁");
//            }
//
//        }, "t4");

        t1.start();
        t2.start();
        t3.start();
//        t4.start();



    }

    public static void method1() {
         readLock.lock();
        try {
            log.debug("正在执行method1方法-------------------");

        } finally {
            readLock.unlock();
        }

    }
}
