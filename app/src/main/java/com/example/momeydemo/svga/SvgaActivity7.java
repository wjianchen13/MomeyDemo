package com.example.momeydemo.svga;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.momeydemo.R;
import com.example.momeydemo.Utils;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import java.util.LinkedList;
import java.util.List;

/**
 * Svga动画测试
 */
public class SvgaActivity7 extends AppCompatActivity {

    private SVGAImageView svgaTest1;
    private List<SVGAVideoEntity> mList;

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svga7);
        mList = new LinkedList<>();
        svgaTest1 = findViewById(R.id.svga_test1);
    }

    /**
     * 开始动画
     * @param v
     */
    public void onTest1(View v) {
        for(int i = 0; i < 50; i ++) {
            Utils.log("加载完成: " + i);
            loadAnim(svgaTest1, "test1.svga");
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "全部加载完成", Toast.LENGTH_SHORT).show();
    }

    private void loadAnim(final SVGAImageView v, String name) {
        SVGAParser.Companion.shareParser().init(this);
        SVGAParser svgaParser = SVGAParser.Companion.shareParser();
//        String name = this.randomSample();
        //asset jojo_audio.svga  cannot callback
        Log.d("SVGA", "## name " + name);
        svgaParser.setFrameSize(1000, 1000);
        svgaParser.decodeFromAssets(name, new SVGAParser.ParseCompletion() {
            @Override
            public void onComplete(@NonNull SVGAVideoEntity videoItem) {
                Log.e("zzzz", "onComplete: ");
//                v.setVideoItem(videoItem);
//                v.stepToFrame(0, true);
                mList.add(videoItem);
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
//        svgaTest1.stopAnimation(true);
        Utils.log("list size: " + mList.size());

    }


    /**
     * 清除所有内存数据
     * @param v
     */
    public void onTest3(View v) {
        if (mList != null) {
            for(int i = 0; i < mList.size(); i ++) {
                mList.get(i).clear();
            }
            mList.clear();
            mList = null;
        }
    }


}