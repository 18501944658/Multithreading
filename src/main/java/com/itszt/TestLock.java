package com.itszt;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {

    private static Lock lock = new ReentrantLock();


    public static void main(String[] args) throws InterruptedException {
        //线程的数量
        int N = 10;
        Thread[] threads = new Thread[N];

        for (int i = 0; i <N; i++) {
          threads[i]= new Thread(new Runnable() {
              @Override
              public void run() {
                 lock.lock();
                  try {
                      System.out.println(Thread.currentThread().getName()+"get sync  lock !");
                  } catch (Exception e) {
                      e.printStackTrace();
                  }finally {
                      lock.unlock();
                  }


              }
          });
        }

     lock.lock();

            for (int i = 0; i < N; i++) {
                threads[i].start();
                Thread.sleep(1000);
            }
        for(int i = 0; i < N; ++i)
            threads[i].join();

    }
}
