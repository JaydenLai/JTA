package com.hz.syxx.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Pefan_Li
 * Created Time 2018/8/26 16:57.
 */
public class ReentrantLockSample {
    private static final Logger logger = LoggerFactory.getLogger(Sample.class);
    private static int count;
    private static final Lock lock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(MyConstant.TOTALTIMES);
        Semaphore semaphore = new Semaphore(MyConstant.POOLSIZE);

        ExecutorService executor= Executors.newCachedThreadPool();
        for (int i=0; i<MyConstant.TOTALTIMES; i++) {
            executor.execute(() -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                add();
                semaphore.release();
            });
            countDownLatch.countDown();
        }
        countDownLatch.await();
        logger.info("Final count： {}",count);
    }

    public static void add(){
        lock.lock();
        try {
            count++;
        }finally {
            lock.unlock();
        }
    }
}
