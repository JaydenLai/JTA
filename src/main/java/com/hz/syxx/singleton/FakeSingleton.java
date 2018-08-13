package com.hz.syxx.singleton;

import com.hz.syxx.annotation.NotThreadSafe;

/**
 * Created by Pefan_Li
 * Created Time 2018/8/13 22:32.
 */
@NotThreadSafe
public class FakeSingleton {
    /**
     * Must be private
     */
    private FakeSingleton(){

    }
    private static FakeSingleton instance = null;

    public static FakeSingleton getInstance(){
        if(instance == null){
            instance = new FakeSingleton();
        }
        return instance;
    }
}
