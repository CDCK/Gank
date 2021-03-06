package com.cdck.android.view.activity.date;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.cdck.android.R;
import com.cdck.android.base.BaseActivity;
import com.cdck.android.model.result.GankInfo;
import com.cdck.android.model.result.NewestSection;
import com.cdck.android.ui.TopBarUI;
import com.cdck.android.view.activity.PreViewActivity;
import com.cdck.android.view.activity.web.WebActivity;
import com.cdck.android.view.newest.NewestSectionAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DateActivity extends BaseActivity<DateContract.Presenter> implements DateContract.View {

    @BindView(R.id.date_smart)
    SmartRefreshLayout dateSmart;
    @BindView(R.id.date_rv)
    RecyclerView dateRv;
    @BindView(R.id.date_topbar)
    TopBarUI dateTopBar;
    private View headView;
    private ImageView imageView;
    private String imgUrl;
    private NewestSectionAdapter sectionAdapter;
    private String dateStr;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_date;
    }

    @Override
    protected DateContract.Presenter initPresenter() {
        return new DatePresenter();
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        String date = bundle.getString("date");
        dateStr = date.replace("-", "/");
        mPresenter.getDateInfo(dateStr);
        dateTopBar.setRightVisibility(false);
        dateTopBar.setTitle(date);
        dateTopBar.setClickCallBack(new TopBarUI.onToolClickListener() {
            @Override
            public void clickTopBarLeft(int resid) {
                finish();
            }

            @Override
            public void clickTopBarRight(int resid) {

            }
        });

        headView = LayoutInflater.from(this).inflate(R.layout.header_newest, null);
        imageView = headView.findViewById(R.id.header_img);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!imgUrl.isEmpty()) {
                    ArrayList<String> urls = new ArrayList<>();
                    urls.add(imgUrl);
                    PreViewActivity.loadPreViewIcon(DateActivity.this, urls);
                }
            }
        });
        dateSmart.setEnableLoadMore(false);
        dateSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (!dateStr.isEmpty()) {
                    mPresenter.getDateInfo(dateStr);
                }
                refreshLayout.finishRefresh(1000);
            }
        });
    }

    List<NewestSection> SectionDatas = new ArrayList<>();

    @Override
    public void updateSectionData(List<NewestSection> datas) {
        SectionDatas.clear();
        for (int i = 0; i < datas.size(); i++) {
            NewestSection newestSection = datas.get(i);
            GankInfo t = newestSection.t;
            if (t != null) {//标题栏中是没有GankInfo对象的
                String type = t.getType();
                if (type.equals("福利")) {
                    imgUrl = t.getUrl();
                } else {
                    SectionDatas.add(newestSection);
                }
            } else {
                if (!newestSection.header.equals("福利")) {
                    SectionDatas.add(new NewestSection(true, newestSection.header));
                }
            }
        }
        if (sectionAdapter == null) {
            sectionAdapter = new NewestSectionAdapter(R.layout.newest_section_item,
                    R.layout.newest_header_item, SectionDatas);
            sectionAdapter.addHeaderView(headView);
            dateRv.setLayoutManager(new LinearLayoutManager(this));
            dateRv.setAdapter(sectionAdapter);
        } else sectionAdapter.notifyDataSetChanged();
        //设置头布局图片
        final LinearLayout headerLayout = sectionAdapter.getHeaderLayout();
        final ImageView headerIv = headerLayout.findViewById(R.id.header_img);
        Glide.with(this)
                .load(imgUrl)
                .addListener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        sectionAdapter.removeHeaderView(headView);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(headerIv);
        sectionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_section:
                        ArrayList<String> urls = new ArrayList<>();
                        List<String> images = SectionDatas.get(position).t.images;
                        if (!images.isEmpty()) {
                            urls.addAll(images);
                            PreViewActivity.loadPreViewIcon(DateActivity.this, urls);
                        }
                        break;
                }
            }
        });
        sectionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (!SectionDatas.get(position).isHeader) {
                    GankInfo gankInfo = SectionDatas.get(position).t;
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(WebActivity.BUNDLE_GANKINFO, gankInfo);
                    WebActivity.loadWebViewActivity(DateActivity.this, bundle);
                }
            }
        });
    }
}
