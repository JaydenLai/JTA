package com.hz.syxx.singleton;

import com.hz.syxx.annotation.NotThreadSafe;
import com.hz.syxx.annotation.ThreadSafe;

/**
 * Created by Pefan_Li
 * Created Time 2018/8/13 22:32.
 */
@ThreadSafe
public class DoubleCheckLazySingleton {
    /**
     * Must be private
     */
    private DoubleCheckLazySingleton(){

    }

    /**
     *  Thread safe as command reorder, must be volatile
     */
    private static volatile DoubleCheckLazySingleton instance = null;

    public static DoubleCheckLazySingleton getInstance(){
        if(instance == null){
            synchronized (DoubleCheckLazySingleton.class) {
                instance = new DoubleCheckLazySingleton();
            }
        }
        return instance;
    }
}
