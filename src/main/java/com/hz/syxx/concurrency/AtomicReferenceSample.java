package com.hz.syxx.concurrency;

import com.hz.syxx.annotation.ThreadSafe;
import com.hz.syxx.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Pefan_Li
 * Created Time 2018/8/9 23:57.
 */
@ThreadSafe
public class AtomicReferenceSample {
    private static final Logger logger = LoggerFactory.getLogger(AtomicIntegerSample.class);
    private static AtomicReference<Integer> atomicReference = new AtomicReference<>(0);
    public static void main(String[] args) {
        atomicReference.compareAndSet(0,1);
        atomicReference.compareAndSet(0,2);
        atomicReference.compareAndSet(1,3);
        atomicReference.compareAndSet(1,4);
        logger.info("final: {}",atomicReference.get());
    }
}
