package com.example.momeydemo.cache3;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.momeydemo.R;

/**
 * DiskLruCache 测试
 */
public class DiskLruCacheActivity2 extends AppCompatActivity {

    private String mUrl = "https://img.ayomet.com/images/default_headimage/v1/head_1_5.png";
    private ImageView imgvTest;
    private DiskLruCacheUtils2 mDiskLruCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disklrucache2);
        imgvTest = findViewById(R.id.imgv_test);
        mDiskLruCache = new DiskLruCacheUtils2(this);
    }

    /**
     * 加载图片
     * @param v
     */
    public void onTest1(View v) {
        mDiskLruCache.loadBitmap(mUrl);
    }

    /**
     * 显示图片
     * @param v
     */
    public void onTest2(View v) {
        imgvTest.setImageBitmap(mDiskLruCache.getCacheBitmap(mUrl));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}