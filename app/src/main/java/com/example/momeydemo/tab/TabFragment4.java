package com.example.momeydemo.tab;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.momeydemo.R;

public class TabFragment4 extends Fragment {

    public static final String TAG = TabFragment4.class.getSimpleName();
    private boolean bUserVisible = false;

    private String LOG = "=============================> ";

    public TabFragment4() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, LOG + "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, LOG + "onCreateView");
        View v = inflater.inflate(R.layout.fragment_tab4, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, LOG + "onActivityCreated");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, LOG + "onViewCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, LOG + "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, LOG + "onResume");

        //当前Fragment用户可见了
        //可以启动网络加载或绘图工作了。
        bUserVisible = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, LOG + "onPause");

        // 当前Fragment用户不可见了，
        // 如果有网络数据加载或绘图工作，可以停止了。
        bUserVisible = false;
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, LOG + "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, LOG + "onDestroy");
    }

}