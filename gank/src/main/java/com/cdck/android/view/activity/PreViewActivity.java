package com.cdck.android.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.cdck.android.R;

import java.util.ArrayList;

public class PreViewActivity extends AppCompatActivity {

    private RequestOptions options;
    public static final String PREVIEW_BUNDLE = "preview_bundle";
    public static final String PREVIEW_BUNDLE_URLS = "preview_bundle_urls";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_view);
        getWindow().setEnterTransition(new Slide().setDuration(500));
        getWindow().setExitTransition(new Slide().setDuration(500));
        options = new RequestOptions()
                .placeholder(R.drawable.shape_white)//占位图
                .error(R.drawable.error_load)//加载失败时图片
//                .skipMemoryCache(true)//禁用掉硬盘缓存
//                .dontAnimate()
                .override(Target.SIZE_ORIGINAL);//指定图片大小，Target.SIZE_ORIGINAL 表示原图大小
        ViewPager viewPager = findViewById(R.id.viewpager);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(PREVIEW_BUNDLE);
        ArrayList<String> strings = bundle.getStringArrayList(PREVIEW_BUNDLE_URLS);
        MyPagerAdapter adapter = new MyPagerAdapter(strings);
        viewPager.setAdapter(adapter);
    }

    public static void loadPreViewIcon(Context context, ArrayList<String> urls) {
        Intent intent = new Intent(context, PreViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(PREVIEW_BUNDLE_URLS, urls);
        intent.putExtra(PREVIEW_BUNDLE, bundle);
        context.startActivity(intent);
    }


    public class MyPagerAdapter extends PagerAdapter {

        private ArrayList<String> datas;

        public MyPagerAdapter(ArrayList<String> datas) {
            this.datas = datas;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView iv = new ImageView(PreViewActivity.this);
            Glide.with(PreViewActivity.this).load(datas.get(position)).apply(options).into(iv);
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return datas != null ? datas.size() : 0;
        }

        @Override
        public boolean isViewFromObject(@NonNull View arg0, @NonNull Object arg1) {
            return arg0 == arg1;
        }
    }
}
