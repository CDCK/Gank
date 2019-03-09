package com.cdck.androidplan.view.belle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.cdck.androidplan.R;
import com.cdck.androidplan.base.BaseFragment;
import com.cdck.androidplan.model.result.GankInfo;
import com.cdck.androidplan.view.activity.PreViewActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by xlk on 2019/1/17.
 */
public class BelleFragment extends BaseFragment<BelleContract.Presenter> implements BelleContract.View {

    @BindView(R.id.belle_rv)
    RecyclerView belleRv;
    @BindView(R.id.belle_srl)
    SmartRefreshLayout belleSrl;
    private int page = 1;
    private BelleAdapter adapter;

    @Override
    public BelleContract.Presenter initPresenter() {
        return new BellePresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_belle;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        belleSrl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
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
        mPresenter.getBelleData(page);
    }

    List<GankInfo> belles = new ArrayList<>();

    @Override
    public void update(List<GankInfo> gankInfos) {
        int from, to;
        if (page == 1) {
            belles.clear();
            from = 0;
            to = gankInfos.size();
        } else {
            from = (page - 1) * 10;
            to = page * 10;
        }
        belles.addAll(gankInfos);
        if (adapter == null) {
            adapter = new BelleAdapter(R.layout.item_belle, belles, getContext());
            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
            belleRv.setLayoutManager(layoutManager);
            belleRv.setHasFixedSize(true);
            belleRv.setAdapter(adapter);
        } else {
            adapter.initImgScale(from, to);
            adapter.notifyDataSetChanged();
        }
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String url = belles.get(position).getUrl();
                ArrayList<String> urls = new ArrayList<>();
                urls.add(url);
                PreViewActivity.loadPreViewIcon(getContext(), urls);
            }
        });
    }
}
