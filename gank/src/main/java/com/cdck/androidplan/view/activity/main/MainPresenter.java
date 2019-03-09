package com.cdck.androidplan.view.activity.main;

import com.cdck.androidplan.base.BasePresenter;
import com.cdck.androidplan.constnt.EventMessage;
import com.cdck.androidplan.constnt.IDKey;
import com.cdck.androidplan.model.result.RHistoyDate;
import com.cdck.androidplan.ui.TopBarValue;
import com.cdck.androidplan.util.RetrofitManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xlk on 2019/1/16.
 */
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {


    @Override
    public void getHistory() {
        RetrofitManager.gankApi().getHistoryDate().enqueue(new Callback<RHistoyDate>() {
            @Override
            public void onResponse(Call<RHistoyDate> call, Response<RHistoyDate> response) {
                mRefView.get().showHistoryDate(response.body());
            }

            @Override
            public void onFailure(Call<RHistoyDate> call, Throwable t) {

            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEventMessage(EventMessage message) {
        switch (message.getKey()) {
//            case IDKey.UPDATE_TOPBAR:
//                Object[] values = message.getValues();
//                TopBarValue value = (TopBarValue) values[0];
//                mRefView.get().updateTopBar(value);
//                break;
        }
    }

    @Override
    public void registEventBus() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void unregistEventBus() {
        EventBus.getDefault().unregister(this);
    }
}
