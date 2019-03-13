package com.cdck.android.view.belle;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.cdck.android.R;
import com.cdck.android.base.BaseFragment;
import com.cdck.android.model.result.GankInfo;
import com.cdck.android.view.activity.PreViewActivity;
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

    @Override
    public void onAttach(Context context) {
        Log.i("F_life", "BelleFragment.onAttach :   --> ");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("F_life", "BelleFragment.onCreate :   --> ");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.i("F_life", "BelleFragment.onActivityCreated :   --> ");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.i("F_life", "BelleFragment.onStart :   --> ");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i("F_life", "BelleFragment.onResume :   --> ");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i("F_life", "BelleFragment.onPause :   --> ");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i("F_life", "BelleFragment.onStop :   --> ");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.i("F_life", "BelleFragment.onDestroyView :   --> ");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.i("F_life", "BelleFragment.onDestroy :   --> ");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.i("F_life", "BelleFragment.onDetach :   --> ");
        super.onDetach();
    }
}
