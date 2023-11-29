package com.example.momeydemo.leak;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.momeydemo.R;

/**
 * 内存泄漏测试
 */
public class TestLeakActivity1 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_leak1);
        MySingleton singleton = MySingleton.getInstance(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("=======================> TestLeakActivity1 onDestroy");
    }

    /**
     * 单例模式泄漏
     * @param v
     */
    public void onTest1(View v) {

    }

    /**
     *
     * @param v
     */
    public void onTest2(View v) {

    }


}