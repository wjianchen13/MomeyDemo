package com.example.momeydemo.cache1;

import android.os.Bundle;
import android.util.LruCache;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.momeydemo.R;
import com.example.momeydemo.Utils;
import com.example.momeydemo.reference.User;

import java.util.Map;

public class LruCacheActivity extends AppCompatActivity {

    private LruCache<String, User> lruCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lrucache);
        lruCache = new LruCache<String, User>(10){

            @Override
            protected int sizeOf(String key, User value) {
                return 1;
            }

            @Override
            protected void entryRemoved(boolean evicted, String key, User oldValue, User newValue) {
                super.entryRemoved(evicted, key, oldValue, newValue);
                if(evicted && oldValue != null) {
                    Utils.log("remove age: " + oldValue.getAge() + "   name: " + oldValue.getName());
                }
            }
        };
    }

    /**
     * 添加数据
     * @param v
     */
    public void onTest1(View v) {
        for(int i = 0; i < 10; i ++) {
            User user = new User(i, "test" + i);
            lruCache.put(i + "", user);
        }
    }

    /**
     * 遍历
     * @param v
     */
    public void onTest2(View v) {
        if (lruCache != null) {
            Map<String, User> map = lruCache.snapshot();
            for (Map.Entry<String, User> entry : map.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue().toString());
            }
        }
    }

    /**
     * 访问一个数据
     * @param v
     */
    public void onTest3(View v) {
        System.out.println(lruCache.get("5"));
    }

    /**
     * 添加一个数据
     * @param v
     */
    public void onTest4(View v) {
        lruCache.put("11", new User(11, "test" + 11));
    }

    /**
     * 添加2个数据
     * @param v
     */
    public void onTest5(View v) {
        lruCache.put("12", new User(12, "test" + 12));
        lruCache.put("13", new User(13, "test" + 13));
    }

    /**
     *
     */
    public void onTest6(View v) {

    }

    /**
     *
     */
    public void onTest7(View v) {

    }

    /**
     *
     */
    public void onTest8(View v) {

    }


    /**
     *
     * @param v
     */
    public void onTest9(View v) {

    }

    /**
     *
     * @param v
     */
    public void onTest10(View v) {

    }

}