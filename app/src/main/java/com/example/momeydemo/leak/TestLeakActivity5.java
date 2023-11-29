package com.example.momeydemo.leak;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.momeydemo.R;

import java.lang.ref.WeakReference;

/**
 * Handler导致的内存泄漏
 */
public class TestLeakActivity5 extends AppCompatActivity {

//    private static class MyHandler extends Handler {
//        private WeakReference<TestLeakActivity5> mActivity;
//
//        public MyHandler(TestLeakActivity5 activity) {
//            mActivity = new WeakReference<TestLeakActivity5>(activity);
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            TestLeakActivity5 activity = mActivity.get();
//            if (activity != null) {
//                // ... handle message ...
//            }
//        }
//    }
//
//    private MyHandler mHandler = new MyHandler(this);

    private class MyHandler extends Handler {
        private TestLeakActivity5 mActivity;

        public MyHandler(TestLeakActivity5 activity) {
            mActivity = activity;
        }

        @Override
        public void handleMessage(Message msg) {
            TestLeakActivity5 activity = mActivity;
            if (activity != null) {
                System.out.println("=======================> TestLeakActivity5 handleMessage");
            }
        }
    }

    private MyHandler mHandler = new MyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_leak5);
        mHandler.sendMessageDelayed(mHandler.obtainMessage(), 10000);
    }

    /**
     *
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