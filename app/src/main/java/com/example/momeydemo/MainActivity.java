package com.example.momeydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.momeydemo.cache1.LruCacheActivity;
import com.example.momeydemo.cache2.DiskLruCacheActivity;
import com.example.momeydemo.cache3.DiskLruCacheActivity2;
import com.example.momeydemo.leak.LeakActivity;
import com.example.momeydemo.reference.ReferenceActivity;
import com.example.momeydemo.svga.SvgaActivity;
import com.example.momeydemo.tab.TabActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 测试Tab + ViewPager2内存
     * @param v
     */
    public void onTest1(View v) {
        startActivity(new Intent(this, TabActivity.class));
    }

    /**
     * 引用测试
     * @param v
     */
    public void onTest2(View v) {
        startActivity(new Intent(this, ReferenceActivity.class));
    }

    /**
     * LruCache
     * @param v
     */
    public void onTest3(View v) {
        startActivity(new Intent(this, LruCacheActivity.class));
    }

    /**
     * 内存泄漏测试
     * @param v
     */
    public void onTest4(View v) {
        startActivity(new Intent(this, LeakActivity.class));
    }

    /**
     * DiskLruCache
     * @param v
     */
    public void onTest5(View v) {
        startActivity(new Intent(this, DiskLruCacheActivity.class));
    }

    /**
     * DiskLruCache 测试
     * @param v
     */
    public void onTest6(View v) {
        startActivity(new Intent(this, DiskLruCacheActivity2.class));
    }

    /**
     * Svga动画测试
     * @param v
     */
    public void onTest7(View v) {
        startActivity(new Intent(this, SvgaActivity.class));
    }


}