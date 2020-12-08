package com.itszt.pool;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 线程
 * 线程可以命名
 * 线程可以执行实现Runnable的接口的run方法
 * 线程还可以从等待队列中拿取任务执行
 */
@Slf4j(topic = "RunThreadNode")
@Getter
public class RunThreadNode extends Thread {

    private RunTask runTask;

    private ThreadBlockQueue threadBlockQueue;

    public RunThreadNode(RunTask runTask, String name, ThreadPoolNode threadPoolNode) {
        setName(name);
        this.runTask = runTask;
        this.threadBlockQueue=threadPoolNode.getBlockQueue();
    }

    @Override
    public void run() {
        while (runTask!=null||(runTask=threadBlockQueue.poll())!=null){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /**执行当前任务**/
            log.debug("当前线程{}---正在执行任务{}", this.getName(), runTask.getTaskname());
            runTask.run();
            runTask=null;
        }
    }
}
