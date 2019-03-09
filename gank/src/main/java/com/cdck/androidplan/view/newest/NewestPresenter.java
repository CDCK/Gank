package com.cdck.androidplan.view.newest;

import com.cdck.androidplan.base.BasePresenter;
import com.cdck.androidplan.model.result.RToday;
import com.cdck.androidplan.util.ConverUtil;
import com.cdck.androidplan.util.RetrofitManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xlk on 2019/1/17.
 */
public class NewestPresenter extends BasePresenter<NewestContract.View> implements NewestContract.Presenter {

    @Override
    public void getNewestData(String date) {
        RetrofitManager.gankApi().getDateInfo(date).enqueue(new Callback<RToday>() {
            @Override
            public void onResponse(Call<RToday> call, Response<RToday> response) {
                uptateTodayInfo(response.body());
            }

            @Override
            public void onFailure(Call<RToday> call, Throwable t) {

            }
        });
    }

    private void uptateTodayInfo(RToday todayData) {
        if (todayData == null || todayData.isError()) {
            return;
        }
        mRefView.get().updateSectionData(ConverUtil.converTo(todayData.getResults()));
    }
}
