package com.example.momeydemo.svga;

import android.content.Intent;
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
public class SvgaActivity extends AppCompatActivity {

    static String ENCODE = "UTF-8";//保持平台兼容统一使用utf-8
    static String DES = "DES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svga);
    }

    /**
     * 单个动画
     * @param v
     */
    public void onTest2(View v) {
        startActivity(new Intent(this, SvgaActivity1.class));
    }

    /**
     * 列表
     * @param v
     */
    public void onTest3(View v) {
        startActivity(new Intent(this, SvgaActivity2.class));
    }

    /**
     * 使用缓存
     * @param v
     */
    public void onTest4(View v) {
        startActivity(new Intent(this, SvgaActivity3.class));
    }

    /**
     * 清除缓存
     * @param v
     */
    public void onTest5(View v) {
        SvgaCache.getInstance().clear();
    }

    // 服务端解密
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
    public void onTest1(View v) {
        try {
            String url = DESAndBase64Decrypt("WDEVgFVF9xof4U3NthyHSg1uStOnn9bShxAFMSNXSTD0fbADgRQde3p8X4v6paPoleLPK98u/fmGO7hQNmRXhuW1wKUKFWMTHP5taoy5137tjRajPW5OJ9WXyvrZM1+CV6hNJieaIQM=", "MIIBIjANBgkqhkiG9w0B");
            Utils.log("url: " + url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}