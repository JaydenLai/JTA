package com.hz.syxx.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Time;
import java.util.concurrent.*;

/**
 * Created by Pefan_Li
 * Created Time 2018/8/25 18:52.
 */
public class CyclicBarrierSample {
    private static final Logger logger = LoggerFactory.getLogger(CyclicBarrierSample.class);
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5, ()->{
        logger.info("run callback");
    });

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i=0; i<10; i++) {
            TimeUnit.SECONDS.sleep(1);
            final int threadNum = i;
            executor.execute(() -> {
                try {
                    move(threadNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
    }

    public static void move(int threadNum) throws InterruptedException, BrokenBarrierException {
        TimeUnit.SECONDS.sleep(1);
        logger.info("ThreadNum {} ready",threadNum);
        cyclicBarrier.await();
        logger.info("ThreadNum {} runned",threadNum);
    }
}
