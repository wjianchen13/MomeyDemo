package com.example.momeydemo.svga;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.momeydemo.R;
import com.example.momeydemo.Utils;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * Svga动画测试
 */
public class SvgaActivity4 extends AppCompatActivity {

    private SVGAImageView svgaTest1;
    static String ENCODE = "UTF-8";//保持平台兼容统一使用utf-8
    static String DES = "DES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svga4);
        svgaTest1 = findViewById(R.id.svga_test1);
    }

    /**
     * 开始动画
     * @param v
     */
    public void onTest1(View v) {
        loadAnim(svgaTest1, "test1.svga");
    }

    private void loadAnim(final SVGAImageView v, String name) {
        SVGAParser.Companion.shareParser().init(this);
        SVGAParser svgaParser = SVGAParser.Companion.shareParser();
//        String name = this.randomSample();
        //asset jojo_audio.svga  cannot callback
        Log.d("SVGA", "## name " + name);
        svgaParser.setFrameSize(100, 100);
        svgaParser.decodeFromAssets(name, new SVGAParser.ParseCompletion() {
            @Override
            public void onComplete(@NonNull SVGAVideoEntity videoItem) {
                Log.e("zzzz", "onComplete: ");
                v.setVideoItem(videoItem);
                v.stepToFrame(0, true);
            }

            @Override
            public void onError() {
                Log.e("zzzz", "onComplete: ");
            }

        }, null);

    }

    /**
     * 停止动画
     * @param v
     */
    public void onTest2(View v) {
        svgaTest1.stopAnimation(true);
//        svgaTest1.clear();
    }

    //服务端解密
    public static String DESAndBase64Decrypt(String dataBase64, String key) throws Exception {
        // Log.d("DESAndBase64Decrypt", dataBase64);
        if (!TextUtils.isEmpty(dataBase64) && dataBase64.length() > 6 && dataBase64.substring(0, 4).contains("http")) {
            return dataBase64;
        } else {
            if (TextUtils.isEmpty(dataBase64)) return null;
            byte[] encryptedData = Base64.decode(dataBase64, Base64.DEFAULT);
            byte[] decryptedData = decryptByteDES(encryptedData, key);
            return new String(decryptedData, ENCODE);
        }

        // LogUtil.d("DESAndBase64Decrypt", textDecrypt);


    }

    //des 解密
    private static byte[] decryptByteDES(byte[] byteD, String strKey) throws Exception {
        return doDecrypt(byteD, getKey(strKey), DES);
    }

    public static SecretKey getKey(String strKey) throws Exception {
        DESKeySpec desKeySpec = new DESKeySpec(strKey.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        return keyFactory.generateSecret(desKeySpec);
    }

    /**
     * 执行解密操作
     *
     * @param data 待操作数据
     * @param key  Key
     * @param type 算法 RSA or DES
     * @return
     * @throws Exception
     */
    private static byte[] doDecrypt(byte[] data, Key key, String type) throws Exception {
        Cipher cipher = Cipher.getInstance(type);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    /**
     * 获取真实Url
     * @param v
     */
    public void onTest3(View v) {
        try {
            String url = DESAndBase64Decrypt("WDEVgFVF9xof4U3NthyHSg1uStOnn9bShxAFMSNXSTD0fbADgRQde3p8X4v6paPovsKsZnRpqNlcOY6HzUaYIxW9mstdCNnM+nqUQ2UF+7vKw8MUwY3jCNWXyvrZM1+CV6hNJieaIQM=", "MIIBIjANBgkqhkiG9w0B");
            Utils.log("url: " + url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}