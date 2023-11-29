package com.example.momeydemo.tab;


import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class ViewPager2Fragment extends Fragment {
    private boolean bUserVisible = false;

    private String tag = null;
    private String LOG = "生命周期->";

    public ViewPager2Fragment(String tag) {
        this.tag = tag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG + tag, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView text = new TextView(getContext());
        text.setText(tag);
        text.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 80);
        text.setTextColor(ContextCompat.getColor(getContext(), android.R.color.holo_red_dark));
        Log.d(LOG + tag, "onCreateView");
        return text;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(LOG + tag, "onActivityCreated");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(LOG + tag, "onViewCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(LOG + tag, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(LOG + tag, "onResume");

        //当前Fragment用户可见了
        //可以启动网络加载或绘图工作了。
        bUserVisible = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(LOG + tag, "onPause");

        // 当前Fragment用户不可见了，
        // 如果有网络数据加载或绘图工作，可以停止了。
        bUserVisible = false;
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(LOG + tag, "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG + tag, "onDestroy");
    }

    public String getTabTag() {
        return tag;
    }

    /**
     * 当前Fragment是否可见（用户在手机屏幕看得见？）。
     *
     * @return
     */
    public boolean isCurFragmentVisible() {
        return bUserVisible;
    }
}