package com.example.momeydemo.svga;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.momeydemo.R;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import java.util.List;

/**
 * Created by baoyz on 2014/6/29.
 */
public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder>{

    private Context mContext;
    private List<String> mDataset;

    public MyAdapter2(Context context, List<String> dataset) {
        super();
        mContext = context;
        mDataset = dataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(), R.layout.item_svga2, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        loadAnim(viewHolder.svgaTest, "test1.svga");
    }

    private void loadAnim(final SVGAImageView v, String name) {
        SVGAParser.Companion.shareParser().init(mContext);
        SVGAParser svgaParser = SVGAParser.Companion.shareParser();
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

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public SVGAImageView svgaTest;

        public ViewHolder(View itemView) {
            super(itemView);
            svgaTest = (SVGAImageView) itemView.findViewById(R.id.svga_test);
        }
    }
}
