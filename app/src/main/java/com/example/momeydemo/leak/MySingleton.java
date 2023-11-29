package com.example.momeydemo.leak;

import android.content.Context;

public class MySingleton {

    private Context mContext;
    private static MySingleton sInstance;

    /**
     * 单例内存泄漏
     * 应该使用ApplicationContext代替Activity或Application的Context
     * @param context
     */
    private MySingleton(Context context) {
//        mContext = context.getApplicationContext();
        mContext = context;
    }

    public static MySingleton getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new MySingleton(context);
        }
        return sInstance;
    }

}
