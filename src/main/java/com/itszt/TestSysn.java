package com.itszt;

public class TestSysn {

    private static Object lock = new Object();


    public static void main(String[] args) throws InterruptedException {
        //线程的数量
        int N = 10;
        Thread[] threads = new Thread[N];

        for (int i = 0; i <N; i++) {
          threads[i]= new Thread(new Runnable() {
              @Override
              public void run() {
                  synchronized (lock){
                      System.out.println(Thread.currentThread().getName()+"get sync  lock !");
                  }
              }
          });
        }


        synchronized (lock){
            for (int i = 0; i < N; i++) {
                threads[i].start();
                Thread.sleep(1000);
            }
        }
    }
}
