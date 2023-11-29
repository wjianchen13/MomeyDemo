package com.example.momeydemo.cache2.utils;

import java.io.File;

import android.content.Context;
import android.os.Environment;
/**
 * ����
 * 
 * */
public class FileUtils {

	/**
	 * getExternalCacheDir()-> /sdcard/Android/data/<application
     *              package>/cache 
     * getCacheDir() -> /data/data/<application package>/cache
	 * @param context
	 * @param uniqueName
	 * @return
	 */
	public static File getDiskCacheDir(Context context, String uniqueName) {
		String cachePath;
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())
				|| !Environment.isExternalStorageRemovable()) {
			cachePath = context.getExternalCacheDir().getPath();
		} else {
			cachePath = context.getCacheDir().getPath();
		}
		return new File(cachePath + File.separator + uniqueName);
	}
}
