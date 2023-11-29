package com.example.momeydemo.leak;

import android.content.Context;

public class MySingleton3 {

    private User mUser;
    private static MySingleton3 sInstance;

    /**
     * 单例内存泄漏
     * 应该使用ApplicationContext代替Activity或Application的Context
     */
    private MySingleton3() {

    }

    public static MySingleton3 getInstance() {
        if (sInstance == null) {
            sInstance = new MySingleton3();
        }
        return sInstance;
    }

    public void registerUser(User user) {
        mUser = user;
    }

    public void unRegisterUser() {
        mUser = null;
    }

}
