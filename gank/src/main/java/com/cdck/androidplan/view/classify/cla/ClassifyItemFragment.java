package com.cdck.androidplan.view.classify.cla;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cdck.androidplan.R;
import com.cdck.androidplan.base.BaseFragment;
import com.cdck.androidplan.model.result.GankInfo;
import com.cdck.androidplan.view.activity.PreViewActivity;
import com.cdck.androidplan.view.activity.web.WebActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by xlk on 2019/3/8.
 */
public class ClassifyItemFragment extends BaseFragment<ClassifyItemContract.Presenter> implements ClassifyItemContract.View {

    @BindView(R.id.page_item_srl)
    SmartRefreshLayout srl;
    @BindView(R.id.page_item_rv)
    RecyclerView rv;

    private static final String GANK_TYPE = "gank_type";
    private int page = 1;
    private String type;

    public static ClassifyItemFragment newInstance(String catalogue) {
        Bundle args = new Bundle();
        args.putString(GANK_TYPE, catalogue);
        ClassifyItemFragment fragment = new ClassifyItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle bundle = getArguments();
        type = bundle.getString(GANK_TYPE);
    }

    @Override
    public ClassifyItemContract.Presenter initPresenter() {
        return new ClassifyItemPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.page_item;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        srl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page += 1;
                refresh();
                refreshLayout.finishLoadMore(1000);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                refresh();
                refreshLayout.finishRefresh(1000);
            }
        });
        refresh();
    }

    @Override
    public void refresh() {
        mPresenter.getCurrentData(type, page);
    }

    List<GankInfo> datas = new ArrayList<>();
    ClassifyItemAdapter adapter;

    @Override
    public void update(List<GankInfo> gankInfo) {
        datas.addAll(gankInfo);
        if (adapter == null) {
            adapter = new ClassifyItemAdapter(R.layout.item_table_type, datas, getContext());
            rv.setLayoutManager(new LinearLayoutManager(getContext()));
            rv.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GankInfo gankInfo1 = datas.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable(WebActivity.BUNDLE_GANKINFO, gankInfo1);
                WebActivity.loadWebViewActivity(getActivity(), bundle);
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.item_table_iv) {
                    ArrayList<String> urls = new ArrayList<>();
                    List<String> images = datas.get(position).getImages();
                    if (!images.isEmpty()) {
                        urls.addAll(images);
                        PreViewActivity.loadPreViewIcon(getContext(), urls);
                    }
                }
            }
        });
    }
}
