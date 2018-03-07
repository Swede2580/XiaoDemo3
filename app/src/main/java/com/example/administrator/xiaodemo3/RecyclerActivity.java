package com.example.administrator.xiaodemo3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ImageView;

import com.example.administrator.xiaodemo3.clazz.Mybean;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RecyclerActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private Banner banner;
    private MyrecyckerAdapter adapter;
    private List<Mybean.ResultBean.DataBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        initView();
        initData();
    }

    private void initData() {
        final List<Integer> arrayList = new ArrayList<>();
        arrayList.add(R.mipmap.a);
        arrayList.add(R.mipmap.b);
        arrayList.add(R.mipmap.c);
        arrayList.add(R.mipmap.d);
        arrayList.add(R.mipmap.e);


        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");

        banner.setImages(arrayList)
                .setDelayTime(2000)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
                .setImageLoader(new BitmapPicasso())
                .setIndicatorGravity(BannerConfig.RIGHT)
                .setBannerTitles(list)
                .start();


        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder().url("http://172.16.54.15:8080/json/dataa.json").build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {

            private String string;

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                string = response.body().string();
                runOnUiThread(new Runnable() {


                    @Override
                    public void run() {
                        Mybean mybean = new Gson().fromJson(string, Mybean.class);
                        data = mybean.getResult().getData();

                        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                        adapter = new MyrecyckerAdapter(data, RecyclerActivity.this);

                        recycler.setLayoutManager(manager);
                        recycler.setAdapter(adapter);

                        initListener();
                    }
                });
            }
        });
    }

    private void initListener() {
        adapter.setOncliclListener(new MyrecyckerAdapter.OncliclListener() {
            @Override
            public void onclick(int position) {
                Intent intent = new Intent(RecyclerActivity.this, ImageActivity.class);

            }
        });
    }

    private void initView() {
        recycler = (RecyclerView) findViewById(R.id.recycler);
        banner = (Banner) findViewById(R.id.banner);

    }
    class BitmapPicasso extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Picasso.with(context).load((Integer) path).into(imageView);
        }
    }
}
