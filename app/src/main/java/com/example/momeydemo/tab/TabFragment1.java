package com.example.momeydemo.tab;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.momeydemo.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class TabFragment1 extends Fragment {
    
    public static final String TAG = TabFragment1.class.getSimpleName();
    private boolean bUserVisible = false;

    private String LOG = "=============================> ";

    public TabFragment1() {
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
        View v = inflater.inflate(R.layout.fragment_tab1, container, false);
        initView(v);
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

    private void initView(View v) {
        final List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Fragment TestFragment = new ViewPager2Fragment("卡" + i);
            fragments.add(TestFragment);
        }

        ViewPager2 vp = v.findViewById(R.id.vp2);
        vp.setOffscreenPageLimit(1);
        MyFragmentAdapter adapter = new MyFragmentAdapter(getActivity(), fragments);
        vp.setAdapter(adapter);
        TabLayout tabLayout = v.findViewById(R.id.tablayout);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, vp, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                ViewPager2Fragment f = (ViewPager2Fragment) fragments.get(position);
                tab.setText(f.getTabTag());
            }
        });
        tabLayoutMediator.attach();
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

    private class MyFragmentAdapter extends FragmentStateAdapter {
        private List<Fragment> fragments;

        public MyFragmentAdapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> list) {
            super(fragmentActivity);
            this.fragments = list;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments.get(position);
        }

        @Override
        public int getItemCount() {
            return fragments.size();
        }
    }


}