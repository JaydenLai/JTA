package com.hz.syxx.singleton;

import com.hz.syxx.annotation.NotRecommended;
import com.hz.syxx.annotation.NotThreadSafe;
import com.hz.syxx.annotation.ThreadSafe;

/**
 * Created by Pefan_Li
 * Created Time 2018/8/13 22:32.
 */
@ThreadSafe
@NotRecommended
public class LazySingleton {
    /**
     * Must be private
     */
    private LazySingleton(){

    }
    private static LazySingleton instance = null;

    public static synchronized LazySingleton getInstance(){
        if(instance == null){
            instance = new LazySingleton();
        }
        return instance;
    }
}
