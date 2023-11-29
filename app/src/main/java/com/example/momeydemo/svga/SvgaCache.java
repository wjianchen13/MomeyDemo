package com.example.momeydemo.svga;

import android.util.LruCache;

import com.opensource.svgaplayer.SVGAVideoEntity;

public class SvgaCache {

    private static SvgaCache INSTANCE;

    private LruCache<String, SVGAVideoEntity> mCache;

    private SvgaCache() {

    }

    public static  SvgaCache getInstance() {
        if (INSTANCE == null) {
            synchronized (SvgaCache.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SvgaCache();
                }
            }
        }
        return INSTANCE;
    }

    public void put(String key, SVGAVideoEntity value) {
        if(mCache == null)
            mCache = new LruCache<String, SVGAVideoEntity>(10) {
                @Override
                protected int sizeOf(String key, SVGAVideoEntity value) {
                    return super.sizeOf(key, value);
                }
            };
        mCache.put(key, value);
    }

    public SVGAVideoEntity get(String key) {
        return mCache != null ? mCache.get(key) : null;
    }

    public void clear() {
        if(mCache != null) {
            mCache.evictAll();
            mCache = null;
        }
    }


}
