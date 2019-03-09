package com.cdck.androidplan.view.collect;

import android.os.Bundle;

import com.cdck.androidplan.R;
import com.cdck.androidplan.base.BaseFragment;
import com.cdck.androidplan.constnt.EventMessage;
import com.cdck.androidplan.constnt.IDKey;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by xlk on 2019/1/17.
 */
public class CollectFragment extends BaseFragment<CollectContract.Presenter> implements CollectContract.View {

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
//        getActivity().setTitle("收藏");
    }

    @Override
    public void refresh() {
    }
}
