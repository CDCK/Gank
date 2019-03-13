package com.cdck.android.view.activity.main;

import com.cdck.android.base.BasePresenter;
import com.cdck.android.model.result.RHistoyDate;
import com.cdck.android.util.RetrofitManager;

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
