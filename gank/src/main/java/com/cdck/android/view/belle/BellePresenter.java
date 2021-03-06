package com.cdck.android.view.belle;

import com.cdck.android.base.BasePresenter;
import com.cdck.android.model.result.GankInfos;
import com.cdck.android.util.RetrofitManager;

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
