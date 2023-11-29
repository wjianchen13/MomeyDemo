package com.example.momeydemo.cache3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.momeydemo.R;
import com.example.momeydemo.cache2.constant.Images;
import com.example.momeydemo.cache2.model.LoaderResult;
import com.example.momeydemo.cache2.utils.BitmapCacheUtils;
import com.example.momeydemo.cache2.utils.DownLoadUtils;
import com.example.momeydemo.cache2.utils.FileUtils;
import com.example.momeydemo.cache2.utils.Md5Utils;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 磁盘缓存工具类
 */
public class DiskLruCacheUtils2 {

    private DiskLruCache mDiskLruCache;
    private static final int DISK_CACHE_SIZE = 1024 * 1024 * 50; // 磁盘缓存的大小为50M
    private static final String DISK_CACHE_SUBDIR = "bitmap"; // 设置缓存的文件名bitmap
    private static final int APP_VERSION = 1;
    private static final int VALUES_COUNT = 1;

    public DiskLruCacheUtils2(Context context) {
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
     * 获取图片
     * @param imageUrl
     * @return
     */
    public Bitmap loadBitmap(String imageUrl) {
        Bitmap bitmap;
        //从缓存中获取
        bitmap = getCacheBitmap(imageUrl);
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
        if (addBitmapToDiskLruCache(imageUrl, mDiskLruCache)) {
            return getCacheBitmap(imageUrl);
        }
        return null;
    }

    /**
     * 该代码需要在子线程中进行 将图片添加到磁盘缓存
     *
     * @param imageUrl
     *            图片的下载地址
     * @param diskLruCache
     *            缓存对象
     * @return
     */
    public boolean addBitmapToDiskLruCache(String imageUrl,
                                                  DiskLruCache diskLruCache) {
        boolean result = false;
        String key = Md5Utils.hashKeyForDisk(imageUrl); // 通过md5加密了这个URL，生成一个key
        try {
            DiskLruCache.Editor editor = diskLruCache.edit(key);// 产生一个editor对象
            if (editor != null) {
                // 创建一个新的输出流 ，创建DiskLruCache时设置一个节点只有一个数据，所以这里的index直接设为0即可
                OutputStream outputStream = editor.newOutputStream(0);
                // 通过地址获取图片数据写入到输出流
                if (DownLoadUtils.downloadUrlToStream(imageUrl,
                        outputStream)) {
                    // 写入成功，提交
                    editor.commit();
                    result = true;
                } else {
                    // 写入失败，中止
                    editor.abort();
                    result = false;
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 从缓存中获取Bitmap对象
     *
     * @param imageUrl
     * @return
     */
    public Bitmap getCacheBitmap(String imageUrl) {
        String key = Md5Utils.hashKeyForDisk(imageUrl);// 把Url转换成KEY
        try {
            DiskLruCache.Snapshot snapShot = mDiskLruCache.get(key);// 通过key获取Snapshot对象
            if (snapShot != null) {
                InputStream is = snapShot.getInputStream(0);// 通过Snapshot对象获取缓存文件的输入流
                Bitmap bitmap = BitmapFactory.decodeStream(is);// 把输入流转换成Bitmap对象
                return bitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
