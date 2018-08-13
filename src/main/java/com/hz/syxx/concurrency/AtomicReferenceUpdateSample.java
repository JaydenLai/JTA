package com.hz.syxx.concurrency;

import com.hz.syxx.annotation.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Created by Pefan_Li
 * Created Time 2018/8/12 18:47.
 */
@ThreadSafe
public class AtomicReferenceUpdateSample {
    private static final Logger logger = LoggerFactory.getLogger(AtomicReferenceUpdateSample.class);
    private static AtomicReferenceFieldUpdater<AtomicReferenceUpdateSample,Integer> updater = AtomicReferenceFieldUpdater.
            newUpdater(AtomicReferenceUpdateSample.class,Integer.class,"count");

    //!-- MUST be volatile --！
    private volatile Integer count;

    public static void main(String[] args) {
        AtomicReferenceUpdateSample sample = new AtomicReferenceUpdateSample();
        updater.compareAndSet(sample,sample.count,100);
        logger.info("value is :{}",sample.count);
    }
}
