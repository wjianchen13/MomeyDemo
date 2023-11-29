package com.example.momeydemo.leak;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.momeydemo.R;

/**
 * 非静态内部类持有外部类引用
 */
public class TestLeakActivity2 extends AppCompatActivity {

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // ... handle message ...
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_leak2);
        mHandler.sendMessageDelayed(mHandler.obtainMessage(), 10000);
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