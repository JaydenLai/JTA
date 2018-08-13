package com.hz.syxx.singleton;

import com.hz.syxx.annotation.NotThreadSafe;

/**
 * Created by Pefan_Li
 * Created Time 2018/8/13 22:32.
 */
@NotThreadSafe
public class FakedDoubleCheckLazySingleton {
    /**
     * Must be private
     */
    private FakedDoubleCheckLazySingleton(){

    }

    /**
     * Not thread safe as command reorder, must be volatile
     */
    private static FakedDoubleCheckLazySingleton instance = null;

    public static FakedDoubleCheckLazySingleton getInstance(){
        if(instance == null){
            synchronized (FakedDoubleCheckLazySingleton.class) {
                instance = new FakedDoubleCheckLazySingleton();
            }
        }
        return instance;
    }
}
