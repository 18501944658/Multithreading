package com.itszt;

import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

     private static ReentrantLock lock= new ReentrantLock();


    public static void main(String[] args) {
        lock.lock();
        try {
            System.out.println("我就是无敌锁 ！！！！");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}
