package com.itszt.pool;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "RunTask")
@Getter
public class RunTask implements Runnable {
    private String taskname;

    public RunTask(String taskname) {
        this.taskname = taskname;
    }

    @Override
    public void run() {
        log.debug("我是任务{}-----我执行完成！！！-------------------------", taskname);
    }
}
