package com.example.momeydemo.leak;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.momeydemo.R;

/**
 * 匿名内部类持有外部类引用
 */
public class TestLeakActivity4 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_leak4);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    System.out.println("=======================> run");
                    try {
                        Thread.sleep(3000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("=======================> TestLeakActivity4 onDestroy");
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