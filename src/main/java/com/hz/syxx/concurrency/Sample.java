package com.hz.syxx.concurrency;

import com.hz.syxx.annotation.NotThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by Pefan_Li
 * Created Time 2018/8/4 11:38 12:53.
 */
@NotThreadSafe
public class Sample {
    private static final Logger logger = LoggerFactory.getLogger(Sample.class);
    private static int finalCount;
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(MyConstant.TOTALTIMES);
        Semaphore semaphore = new Semaphore(MyConstant.POOLSIZE);

        ExecutorService executor=Executors.newCachedThreadPool();
        for (int i=0; i<MyConstant.TOTALTIMES; i++) {
            executor.execute(() -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finalCount++;
                semaphore.release();
            });
            countDownLatch.countDown();
        }
        countDownLatch.await();
        logger.info("Final count： {}",finalCount);
    }
}
