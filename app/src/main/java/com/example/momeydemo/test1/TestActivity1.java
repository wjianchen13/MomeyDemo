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
     * 获取内存基本信息
     * @param v
     */
    public void onTest1(View v) {
        getMaxMemoryInfo();
    }

    private void getMaxMemoryInfo() {
        Runtime rt = Runtime.getRuntime();
        long maxMemory = rt.maxMemory();

        // 表示当前应用进程的 Java/ART 堆最多允许增长到多大
        Log.e("XMemory", "Dalvik MaxMemory: " + Long.toString(maxMemory / (1024 * 1024)) + "M");

        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

        //表示系统为普通应用建议的、近似的单应用 Java 堆内存等级，单位本身就是 MB，不需要再除以 1024 * 1024。
        Log.e("XMemory", "Dalvik MemoryClass: " + Long.toString(activityManager.getMemoryClass()) + "M");

        // 表示应用开启“大堆模式”后对应的近似 Java 堆内存等级，单位也是 MB。
        Log.e("XMemory", "Dalvik LargeMemoryClass: " + Long.toString(activityManager.getLargeMemoryClass()) + "M");

        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(info);

        // 表示 Linux 内核可以访问的设备物理内存总量，原始单位是字节。
        Log.e("XMemory", "系统总内存: " + (info.totalMem / (1024 * 1024)) + "M");

        // 表示当前整个系统可供系统和应用继续使用的内存，原始单位是字节。
        Log.e("XMemory", "系统剩余内存: " + (info.availMem / (1024 * 1024)) + "M");

        // 这是系统对当前全局内存压力的判断。
        Log.e("XMemory", "系统是否处于低内存运行：" + info.lowMemory);

        // 表示系统判定进入低内存状态时使用的 availMem 阈值。
        Log.e("XMemory", "系统剩余内存低于 " + (info.threshold / (1024 * 1024)) + "M 时为低内存运行");
    }

    /**
     *
     * @param v
     */
    public void onTest2(View v) {

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
    public void onTest7(View v) {

    }



}