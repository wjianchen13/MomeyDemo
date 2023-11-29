package com.example.momeydemo.leak;

import android.content.Intent;
import android.os.Bundle;
import android.util.LruCache;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.momeydemo.R;
import com.example.momeydemo.reference.User;

import java.util.Map;

/**
 * 内存泄漏测试
 */
public class LeakActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak);
    }

    /**
     * 单例模式泄漏
     * @param v
     */
    public void onTest1(View v) {
        startActivity(new Intent(this, TestLeakActivity1.class));
    }

    /**
     * 非静态内部类持有外部类引用
     * @param v
     */
    public void onTest2(View v) {
        startActivity(new Intent(this, TestLeakActivity2.class));
    }

    /**
     * 注册广播接收器未注销
     * @param v
     */
    public void onTest3(View v) {
        startActivity(new Intent(this, TestLeakActivity3.class));
    }

    /**
     * 匿名内部类持有外部类引用
     * @param v
     */
    public void onTest4(View v) {
        startActivity(new Intent(this, TestLeakActivity4.class));
    }

    /**
     * Handler导致的内存泄漏
     * @param v
     */
    public void onTest5(View v) {
        startActivity(new Intent(this, TestLeakActivity5.class));
    }

    /**
     *
     */
    public void onTest6(View v) {

    }

    /**
     *
     */
    public void onTest7(View v) {

    }

    /**
     *
     */
    public void onTest8(View v) {

    }


    /**
     *
     * @param v
     */
    public void onTest9(View v) {

    }

    /**
     *
     * @param v
     */
    public void onTest10(View v) {

    }

}