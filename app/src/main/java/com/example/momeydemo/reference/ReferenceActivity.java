package com.example.momeydemo.reference;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.momeydemo.R;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class ReferenceActivity extends AppCompatActivity {

    private User mUser;
    private SoftReference<User> mSr;
    private WeakReference<User> mWr;
    private WeakReference<AppCompatActivity> mWr1;

    private ReferenceQueue<User> mRq;
    private PhantomReference<User> mPr;

    private Map<String, SoftReference<User>> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference);


    }

    /**
     * 强引用(StrongReference)
     * @param v
     */
    public void onTest1(View v) {
        mUser = new User();
    }

    /**
     * 强引用(StrongReference)
     * @param v
     */
    public void onTest2(View v) {
        int b = 2;
        System.out.println("========> mUser: " + mUser);
    }

    /**
     * 软引用(SoftReference)
     * @param v
     */
    public void onTest3(View v) {
        User user = new User();
        mSr = new SoftReference(user);
    }

    /**
     * 软引用(SoftReference)
     * @param v
     */
    public void onTest4(View v) {
        User user = mSr.get();
        System.out.println("========> 软引用 user: " + user);
    }

    /**
     * 弱引用(WeakReference)
     * @param v
     */
    public void onTest5(View v) {
        User user = new User(1, "弱引用测试");
        mWr = new WeakReference(user);
        mWr1 = new WeakReference<>(this);
        System.out.println("========> 弱引用 activity: " + this);
    }

    /**
     * 弱引用(WeakReference)
     * @param v
     */
    public void onTest6(View v) {
        User user = mWr.get();
        System.out.println("========> 弱引用 user = null:" + (user == null) + "   user: " + user);
        AppCompatActivity activity = mWr1.get();
        System.out.println("========> 弱引用 Activity = null:" + (activity == null) + "   activity: " + activity);
    }

    /**
     * 虚引用(SoftReference)
     * @param v
     */
    public void onTest7(View v) {
        User user = new User();
        mRq = new ReferenceQueue<>();
        mPr = new PhantomReference(user, mRq);
    }

    /**
     * 虚引用(SoftReference)
     * @param v
     */
    public void onTest8(View v) {
        User user = mPr.get();
        System.out.println("========> 虚引用 user: " + user);
    }


    /**
     * map
     * @param v
     */
    public void onTest9(View v) {
        map = new HashMap<>();
        User user = new User(1, "hello");
        SoftReference sr = new SoftReference(user);
        map.put("1", sr);
    }

    /**
     * map
     * @param v
     */
    public void onTest10(View v) {
        SoftReference<User> sr = map.get("1");
        if(sr != null && sr.get() != null) {
            System.out.println("========> age: " + sr.get().getAge() + "   name: " + sr.get().getName());
        }
    }

}