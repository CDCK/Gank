package com.cdck.androidplan.view.activity.main;

import com.cdck.androidplan.base.BasePresenter;
import com.cdck.androidplan.model.result.RHistoyDate;
import com.cdck.androidplan.util.RetrofitManager;

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
}
