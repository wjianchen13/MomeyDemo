package com.example.momeydemo.cache2.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.momeydemo.R;
import com.example.momeydemo.cache2.constant.Images;
import com.example.momeydemo.cache2.model.LoaderResult;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 磁盘缓存工具类
 */
public class DiskLruCacheUtils {


    private DiskLruCache mDiskLruCache;
    private static final int DISK_CACHE_SIZE = 1024 * 1024 * 50; // 磁盘缓存的大小为50M
    private static final String DISK_CACHE_SUBDIR = "bitmap1"; // 设置缓存的文件名bitmap
    private static final int APP_VERSION = 1;
    private static final int VALUES_COUNT = 1;

    private static final int CPU_COUNT = Runtime.getRuntime()
            .availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;// 核心线程数
    private ExecutorService pool;// 线程池
    private Future future;
    private static final String TAG = "DiskLruCacheUtils";
    public static final int MESSAGE_POST_RESULT = 1;
    private ListView mListView;// ListView的实例
    private Handler mMainHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            LoaderResult result = (LoaderResult) msg.obj;
            ImageView imageView = result.imageView;
            String uri = (String) imageView.getTag();
            if (uri.equals(result.uri)) {
                imageView.setImageBitmap(result.bitmap);
            } else {
                imageView.setImageResource(R.mipmap.ic_launcher);
                Log.w(TAG, "set image bitmap,but url has changed, ignored!");
            }
        }

        ;
    };


    public DiskLruCacheUtils(Context context, ListView listView) {
        mListView = listView;
        pool = Executors.newFixedThreadPool(CORE_POOL_SIZE);
        initDiskLruCache(context);
    }

    /**
     * 初始化
     * @param context
     */
    public void initDiskLruCache(Context context) {
        try {
            File cacheDir = FileUtils.getDiskCacheDir(context, DISK_CACHE_SUBDIR);
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            mDiskLruCache = DiskLruCache.open(cacheDir, APP_VERSION, VALUES_COUNT, DISK_CACHE_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示图片
     *
     * @param imageView
     * @param imageUrl
     */
    public void showImage(final ImageView imageView, final String imageUrl) {
        imageView.setTag(imageUrl);
        // 从缓存中获取
        Runnable loadBitmapTask = new Runnable() {

            @Override
            public void run() {
                if (!Thread.currentThread().isInterrupted()) {
                    Bitmap bitmap = loadBitmapFromDishLruCache(imageUrl);
                    if (bitmap != null) {
                        LoaderResult result = new LoaderResult(imageView, imageUrl,
                                bitmap);
                        mMainHandler.obtainMessage(MESSAGE_POST_RESULT, result)
                                .sendToTarget();
                    }
                }
            }
        };
        future = pool.submit(loadBitmapTask);
    }

    /**
     * 加载Bitmap对象。
     *
     * @param start 第一个可见的ImageView的下标
     * @param end   最后一个可见的ImageView的下标
     */
    public void showIamges(int start, int end) {
        for (int i = start; i < end; i++) {
            String imageUrl = Images.imageUrls[i];
            //从缓存中取图片
            ImageView imageView = (ImageView) mListView.findViewWithTag(imageUrl);
            loadImage(imageUrl, imageView);
        }
    }

    /**
     * 加载图片
     * @param imageUrl 图片的下载路径
     * @param imageView
     */
    public void loadImage(final String imageUrl, final ImageView imageView) {
        imageView.setTag(imageUrl);
        // 从缓存中获取
        Runnable loadBitmapTask = new Runnable() {

            @Override
            public void run() {
                if (!Thread.currentThread().isInterrupted()) {
                    Bitmap bitmap = loadBitmap(imageUrl);
                    if (bitmap != null) {
                        LoaderResult result = new LoaderResult(imageView, imageUrl,
                                bitmap);
                        mMainHandler.obtainMessage(MESSAGE_POST_RESULT, result)
                                .sendToTarget();
                    }
                    Log.e(TAG,"---->Thread run");
                }
            }
        };
        future = pool.submit(loadBitmapTask);
    }

    /**
     * 取消所有任务
     */
    public void cancelAllTask() {
        future.cancel(true);
    }

    /**
     * 从缓存中加载图片
     * @param imageUrl
     * @return
     */
    private Bitmap loadBitmapFromDishLruCache(String imageUrl) {
        Bitmap bitmap;
        //从缓存中获取
        bitmap = BitmapCacheUtils.getCacheBitmap(imageUrl, mDiskLruCache);
        return bitmap;
    }

    /**
     * 获取图片
     * @param imageUrl
     * @return
     */
    private Bitmap loadBitmap(String imageUrl) {
        Bitmap bitmap;
        //从缓存中获取
        bitmap = BitmapCacheUtils.getCacheBitmap(imageUrl, mDiskLruCache);
        if (bitmap != null) {
            return bitmap;
        }
        try {
            bitmap = loadBitmapFromHttp(imageUrl);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bitmap;
    }


    /**
     * 磁盘缓存的添加
     *
     * @param imageUrl
     * @return
     * @throws IOException
     */
    private Bitmap loadBitmapFromHttp(String imageUrl) throws IOException {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new RuntimeException("can not visit network from UI Thread.");
        }
        if (mDiskLruCache == null) {
            return null;
        }
        if (BitmapCacheUtils.addBitmapToDiskLruCache(imageUrl, mDiskLruCache)) {
            return BitmapCacheUtils.getCacheBitmap(imageUrl, mDiskLruCache);
        }
        return null;
    }
}
