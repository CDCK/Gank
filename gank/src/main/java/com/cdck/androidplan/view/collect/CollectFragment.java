package com.cdck.androidplan.view.collect;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.cdck.androidplan.R;
import com.cdck.androidplan.base.BaseFragment;
import com.cdck.androidplan.model.result.GankInfo;
import com.cdck.androidplan.view.activity.web.WebActivity;
import com.cdck.androidplan.view.classify.cla.ClassifyItemAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by xlk on 2019/1/17.
 */
public class CollectFragment extends BaseFragment<CollectContract.Presenter> implements CollectContract.View {

    public static List<GankInfo> collectDatas = new ArrayList<>();
    @BindView(R.id.collect_rv)
    RecyclerView collectRv;
    private ClassifyItemAdapter adapter;

    @Override
    public CollectContract.Presenter initPresenter() {
        return new CollectPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_collect;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        collectRv.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void refresh() {
        if (adapter == null) {
            adapter = new ClassifyItemAdapter(R.layout.item_table_type, collectDatas, getContext());
            collectRv.setAdapter(adapter);
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    GankInfo gankInfo = collectDatas.get(position);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(WebActivity.BUNDLE_GANKINFO, gankInfo);
//                    bundle.putString(WebActivity.BUNDLE_URL, gankInfo.getUrl());
//                    bundle.putString(WebActivity.BUNDLE_TITLE, gankInfo.getDesc());
                    WebActivity.loadWebViewActivity(getActivity(), bundle);
                }
            });
        } else adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        Log.i("F_life", "CollectFragment.onResume :   --> ");
        refresh();
        super.onResume();
    }
}
