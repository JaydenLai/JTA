package com.hz.syxx.singleton;

import com.hz.syxx.annotation.Recommended;
import com.hz.syxx.annotation.ThreadSafe;

/**
 * Created by Pefan_Li
 * Created Time 2018/8/13 23:09.
 */
@ThreadSafe
@Recommended
public class EnumSingleton {
    /**
     * Must be private
     */
    private EnumSingleton(){

    }

    public static EnumSingleton getInstance(){
        return Singleton.INSTANCE.getSingleton();
    }

    private enum Singleton{
        INSTANCE;

        private EnumSingleton enumSingleton;

        /**
         * JVM to ensure this method will only be called once
         */
        Singleton(){
            enumSingleton = new EnumSingleton();
        }

        public EnumSingleton getSingleton(){
            return enumSingleton;
        }
    }
}
