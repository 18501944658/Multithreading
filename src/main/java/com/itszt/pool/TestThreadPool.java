package com.itszt.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j(topic = "main")
public class TestThreadPool {
    public static void main1(String[] args) {
        ThreadPoolNode threadPoolNode = new ThreadPoolNode(2, 3, new ThreadBlockQueue(2));
        for (int i = 1; i < 6; i++) {
            threadPoolNode.submitTask(new RunTask(i + ""));
        }
    }

    public static void main4(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(2)
                , (runnable) -> {
            return new ThreadFactoryPool("t" + atomicInteger.incrementAndGet()).newThread(runnable);
        }, new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 1; i < 11; i++) {
            threadPoolExecutor.submit(() -> {
                log.debug("当前线程正在执行任务------");
            });
        }
    }


    public static void main3(String[] args) {
        int count1 = 0;
        retry:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                count1++;
                if (count1 == 4) {
                    break retry;
                }
                System.out.print(count1 + " ");
            }
        }
        System.out.println("\n\r");

        int count = 0;
        retry:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                count++;
                if (count == 4) {
                    continue retry;
                }
                System.out.print(count + " ");
            }
        }

    }


    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 3, 0,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(2),
                (runnable) -> {
                    return new ThreadFactoryPool("t" + atomicInteger.incrementAndGet()).newThread(runnable);
                }, new ThreadPoolExecutor.CallerRunsPolicy());

        AtomicInteger y = new AtomicInteger(0);

        for (int i = 1; i <=5; i++) {
            log.debug("当前开始执行任务{}",(i));
            threadPoolExecutor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("当前线程{}正在执行任务-{}", Thread.currentThread().getName(), y.incrementAndGet());
            });
        }

    }


}
