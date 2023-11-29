package com.example.momeydemo.cache2;

import android.os.Bundle;
import android.util.LruCache;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.momeydemo.R;
import com.example.momeydemo.cache2.adapter.ImageAdapter;
import com.example.momeydemo.cache2.constant.Images;
import com.example.momeydemo.reference.User;

import java.util.Map;

public class DiskLruCacheActivity extends AppCompatActivity {

    ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disklrucache);
        ListView lv = (ListView) findViewById(R.id.lv);
        adapter = new ImageAdapter(DiskLruCacheActivity.this, Images.imageUrls, lv);
        lv.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.mDiskLruCacheUtils.cancelAllTask();
    }

}