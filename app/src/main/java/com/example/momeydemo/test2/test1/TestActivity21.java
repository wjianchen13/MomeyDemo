package com.example.momeydemo.test2.test1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.momeydemo.MyApplication;
import com.example.momeydemo.R;

/**
 * 测试Activity泄漏
 */
public class TestActivity21 extends AppCompatActivity {

    private ImageView imgvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test21);
        imgvTest = findViewById(R.id.imgv_test);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_test);
        imgvTest.setImageBitmap(bitmap);
    }

    /**
     * 获取内存基本信息
     * @param v
     */
    public void onTest1(View v) {
        MyApplication.getApp().addActivity(this);
    }


}
