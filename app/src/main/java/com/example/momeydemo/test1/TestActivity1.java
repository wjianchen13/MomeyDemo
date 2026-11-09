package com.example.momeydemo.test1;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.momeydemo.R;

/**
 * 内存基础测试
 */
public class TestActivity1 extends AppCompatActivity {

    static String ENCODE = "UTF-8";//保持平台兼容统一使用utf-8
    static String DES = "DES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
    }

    /**
     *
     * @param v
     */
    public void onTest2(View v) {
        getMaxMemoryInfo();
    }

    private void getMaxMemoryInfo() {
        Runtime rt = Runtime.getRuntime();
        long maxMemory = rt.maxMemory();
        Log.e("XMemory", "Dalvik MaxMemory:" + Long.toString(maxMemory / (1024 * 1024)));
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        Log.e("XMemory", "Dalvik MemoryClass:" + Long.toString(activityManager.getMemoryClass()));
        Log.e("XMemory", "Dalvik LargeMemoryClass:" + Long.toString(activityManager.getLargeMemoryClass()));

        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(info);
        Log.e("XMemory", "系统总内存:" + (info.totalMem / (1024 * 1024)) + "M");
        Log.e("XMemory", "系统剩余内存:" + (info.availMem / (1024 * 1024)) + "M");
        Log.e("XMemory", "系统是否处于低内存运行：" + info.lowMemory);
        Log.e("XMemory", "系统剩余内存低于" + (info.threshold / (1024 * 1024)) + "M时为低内存运行");
    }


    /**
     *
     * @param v
     */
    public void onTest3(View v) {

    }

    /**
     *
     * @param v
     */
    public void onTest4(View v) {

    }

    /**
     *
     * @param v
     */
    public void onTest5(View v) {

    }

    /**
     *
     * @param v
     */
    public void onTest6(View v) {

    }

    /**
     * 获取真实Url
     * @param v
     */
    public void onTest1(View v) {

    }


    /**
     * 获取真实Url
     * @param v
     */
    public void onTest7(View v) {

    }
}