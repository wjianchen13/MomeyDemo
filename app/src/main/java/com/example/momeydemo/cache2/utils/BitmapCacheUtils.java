package com.example.momeydemo.cache2.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.jakewharton.disklrucache.DiskLruCache;
import com.jakewharton.disklrucache.DiskLruCache.Editor;

public class BitmapCacheUtils {

	/**
	 * 该代码需要在子线程中进行 将图片添加到磁盘缓存
	 *
	 * @param imageUrl
	 *            图片的下载地址
	 * @param diskLruCache
	 *            缓存对象
	 * @return
	 */
	public static boolean addBitmapToDiskLruCache(String imageUrl,
			DiskLruCache diskLruCache) {
		boolean result = false;
		String key = Md5Utils.hashKeyForDisk(imageUrl); // 通过md5加密了这个URL，生成一个key
		try {
			Editor editor = diskLruCache.edit(key);// 产生一个editor对象
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
	public static Bitmap getCacheBitmap(String imageUrl,DiskLruCache diskLruCache) {
		String key = Md5Utils.hashKeyForDisk(imageUrl);// 把Url转换成KEY
		try {
			DiskLruCache.Snapshot snapShot = diskLruCache.get(key);// 通过key获取Snapshot对象
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
