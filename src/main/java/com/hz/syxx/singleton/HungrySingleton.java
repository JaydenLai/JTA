package com.hz.syxx.singleton;

import com.hz.syxx.annotation.NotRecommended;
import com.hz.syxx.annotation.ThreadSafe;

/**
 * Created by Pefan_Li
 * Created Time 2018/8/13 22:32.
 */
@ThreadSafe
public class HungrySingleton {
    /**
     * Must be private
     */
    private HungrySingleton(){

    }
    private static HungrySingleton instance = new HungrySingleton();

    public static HungrySingleton getInstance(){
        return instance;
    }
}
