package com.example.momeydemo.svga;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.momeydemo.Utils;
import com.opensource.svgaplayer.SVGAImageView;

public class MySVGAImageView extends SVGAImageView {

    public MySVGAImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MySVGAImageView(@NonNull Context context) {
        super(context);
    }

    public MySVGAImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Utils.log("MySVGAImageView onAttachedToWindow");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Utils.log("MySVGAImageView onDetachedFromWindow");
    }


}
