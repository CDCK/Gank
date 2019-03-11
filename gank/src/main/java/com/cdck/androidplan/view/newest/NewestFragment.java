package com.cdck.androidplan.view.newest;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
import com.cdck.androidplan.R;
import com.cdck.androidplan.base.BaseFragment;
import com.cdck.androidplan.model.result.GankInfo;
import com.cdck.androidplan.model.result.NewestSection;
import com.cdck.androidplan.util.LogU;
import com.cdck.androidplan.view.activity.PreViewActivity;
import com.cdck.androidplan.view.activity.main.MainActivity;
import com.cdck.androidplan.view.activity.web.WebActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by xlk on 2019/1/17.
 */
public class NewestFragment extends BaseFragment<NewestContract.Presenter> implements NewestContract.View {

    @BindView(R.id.f_newest_rv)
    RecyclerView fNewestRv;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smartRefresh;
    private String imgUrl = "";
    private View headView;
    private ImageView imageView;
    private NewestSectionAdapter sectionAdapter;

    @Override
    public NewestContract.Presenter initPresenter() {
        return new NewestPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_newest;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        headView();
        smartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refresh();
                refreshLayout.finishRefresh(1000);
            }
        });
    }

    private void headView() {
        headView = LayoutInflater.from(getContext()).inflate(R.layout.header_newest, null);
        imageView = headView.findViewById(R.id.header_img);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!imgUrl.isEmpty()) {
                    ArrayList<String> urls = new ArrayList<>();
                    urls.add(imgUrl);
                    PreViewActivity.loadPreViewIcon(getContext(), urls);
                }
            }
        });
    }

    @Override
    public void refresh() {
        mPresenter.getNewestData(MainActivity.CURRENT_URL);
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
            LogU.i("NewestFragment -->", "headView是否为空：" + (headView == null));
            sectionAdapter.addHeaderView(headView);
            fNewestRv.setLayoutManager(new LinearLayoutManager(getContext()));
            fNewestRv.setAdapter(sectionAdapter);
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
                            PreViewActivity.loadPreViewIcon(getContext(), urls);
                        }
                        break;
                }
            }
        });
        sectionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GankInfo gankInfo = SectionDatas.get(position).t;
//                String url = gankInfo.getUrl();
//                String desc = gankInfo.getDesc();
//                if (!url.isEmpty()) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(WebActivity.BUNDLE_GANKINFO, gankInfo);
//                    bundle.putString(WebActivity.BUNDLE_URL, url);
//                    bundle.putString(WebActivity.BUNDLE_TITLE, desc);
                    WebActivity.loadWebViewActivity(getActivity(), bundle);
//                }
            }
        });
    }
}
