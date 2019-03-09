package com.cdck.androidplan.view.activity.date;

import com.cdck.androidplan.base.BasePresenter;
import com.cdck.androidplan.model.result.RToday;
import com.cdck.androidplan.util.ConverUtil;
import com.cdck.androidplan.util.RetrofitManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xlk on 2019/3/5.
 */
public class DatePresenter extends BasePresenter<DateContract.View> implements DateContract.Presenter {

    @Override
    public void getDateInfo(String date) {
        RetrofitManager.gankApi().getDateInfo(date).enqueue(new Callback<RToday>() {
            @Override
            public void onResponse(Call<RToday> call, Response<RToday> response) {
                RToday body = response.body();
                mRefView.get().updateSectionData(ConverUtil.converTo(body.getResults()));
            }

            @Override
            public void onFailure(Call<RToday> call, Throwable t) {
                showToast(t.getMessage());
            }
        });
    }
}
