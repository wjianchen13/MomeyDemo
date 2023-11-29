package com.example.momeydemo.leak;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.momeydemo.R;

/**
 * 注册广播接收器未注销
 */
public class TestLeakActivity3 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_leak3);
        User user = new User();
        MySingleton3.getInstance().registerUser(user);
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