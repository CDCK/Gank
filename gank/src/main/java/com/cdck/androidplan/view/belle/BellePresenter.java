package com.cdck.androidplan.view.belle;

import com.cdck.androidplan.base.BasePresenter;
import com.cdck.androidplan.model.result.GankInfos;
import com.cdck.androidplan.util.RetrofitManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xlk on 2019/1/17.
 */
public class BellePresenter extends BasePresenter<BelleContract.View> implements BelleContract.Presenter {


    @Override
    public void getBelleData(int page) {
        RetrofitManager.gankApi().getCatalogue("福利",page).enqueue(new Callback<GankInfos>() {
            @Override
            public void onResponse(Call<GankInfos> call, Response<GankInfos> response) {
                mRefView.get().update(response.body().getResults());
            }

            @Override
            public void onFailure(Call<GankInfos> call, Throwable t) {

            }
        });
    }
}
