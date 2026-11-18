package com.example.momeydemo;

import android.app.Application;

import androidx.appcompat.app.AppCompatActivity;

import com.example.momeydemo.test1.TestActivity1;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {

    private List<AppCompatActivity> mActivityList = new ArrayList<>();
    private static MyApplication mApp = null;

    @Override
    public void onCreate() {
        mApp = this;
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        mApp = this;
        super.onTerminate();
    }

    public static MyApplication getApp(){
        return mApp;
    }

    public void addActivity(AppCompatActivity activity) {
        this.mActivityList.add(activity);
    }

}
