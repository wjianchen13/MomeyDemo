package com.example.momeydemo.svga;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.momeydemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Svga动画测试
 */
public class SvgaActivity2 extends AppCompatActivity {

    private MyAdapter2 adapter;

    private LinearLayoutManager layoutManager;
    private RecyclerView rvSvga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svga2);
        initRv();
    }

    public void initRv(){
        rvSvga = findViewById(R.id.rv_svga);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvSvga.setLayoutManager(layoutManager);
        List<String> datas = new ArrayList<>();
        String s1 = "test1";
        datas.add(s1);
        String s2 = "test2";
        datas.add(s2);
        String s3 = "test2";
        datas.add(s3);
        String s4 = "test2";
        datas.add(s4);
        String s5 = "test2";
        datas.add(s5);

        adapter = new MyAdapter2(this, datas);
        rvSvga.setAdapter(adapter);
    }

}