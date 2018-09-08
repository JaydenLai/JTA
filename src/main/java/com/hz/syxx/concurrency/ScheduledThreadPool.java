package com.hz.syxx.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Pefan_Li
 * Created Time 2018/9/8 21:41.
 */
public class ScheduledThreadPool {
    private static final Logger logger = LoggerFactory.getLogger(AtomicIntegerSample.class);
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);

        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                logger.info("Hello Jayden, what can I help?");
            }
        },1,3, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(10);
        exec.shutdown();
    }
}
