package com.cdck.android.view.activity.history;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cdck.android.R;
import com.cdck.android.base.BaseActivity;
import com.cdck.android.model.result.HistoryData;
import com.cdck.android.ui.TopBarUI;
import com.cdck.android.view.activity.date.DateActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class HistoryActivity extends BaseActivity<HistoryContract.Presenter> implements HistoryContract.View {

    @BindView(R.id.history_topbar)
    TopBarUI topBarUI;
    @BindView(R.id.history_rv)
    RecyclerView historyRv;
    @BindView(R.id.history_smart_refresh)
    SmartRefreshLayout smartRefreshLayout;
    private int f = 0, t = 10;
    private HistoryAdapter adapter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_history;
    }

    @Override
    protected HistoryContract.Presenter initPresenter() {
        return new HistoryPresenter();
    }

    @Override
    protected void init() {
        refresh(f, t);
        historyRv.setLayoutManager(new LinearLayoutManager(this));
        topBarUI.setRightVisibility(false);
        topBarUI.setClickCallBack(new TopBarUI.onToolClickListener() {
            @Override
            public void clickTopBarLeft(int resid) {
                onBackPressed();
            }

            @Override
            public void clickTopBarRight(int resid) {

            }
        });

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                f = 0;
                t = 10;
                refresh(f, t);
                refreshLayout.finishRefresh(1000);
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                f = t;
                t += 10;
                refresh(f, t);
                refreshLayout.finishLoadMore(1000);
            }
        });
    }

    private void refresh(int f, int t) {
        mPresenter.getHistoryDate(f, t);
    }

    private List<HistoryData> mData = new ArrayList<>();

    @Override
    public void updateAdapter(int f, List<HistoryData> historyInfo) {
        if (f == 0) mData.clear();
        mData.addAll(historyInfo);
        if (adapter == null) {
            adapter = new HistoryAdapter(R.layout.item_history, mData);
            historyRv.setAdapter(adapter);
        } else adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HistoryData data = mData.get(position);
                String date = data.getDate();
                Bundle bundle = new Bundle();
                bundle.putString("date", date);
                jumpTo(DateActivity.class, bundle);
            }
        });
    }
}
