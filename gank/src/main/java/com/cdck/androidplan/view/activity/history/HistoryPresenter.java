package com.cdck.androidplan.view.activity.history;

import android.text.TextUtils;

import com.cdck.androidplan.base.BasePresenter;
import com.cdck.androidplan.constnt.EventMessage;
import com.cdck.androidplan.model.result.RHistoryDayInfo;
import com.cdck.androidplan.model.result.RHistoyDate;
import com.cdck.androidplan.model.result.GankInfo;
import com.cdck.androidplan.model.result.HistoryData;
import com.cdck.androidplan.model.result.RToday;
import com.cdck.androidplan.util.DateUtil;
import com.cdck.androidplan.util.LogU;
import com.cdck.androidplan.util.RetrofitManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xlk on 2019/2/26.
 */
public class HistoryPresenter extends BasePresenter<HistoryContract.View> implements HistoryContract.Presenter {

    private List<HistoryData> mData = new ArrayList<>();

    @Override
    public void getHistoryDate(final int f, final int t) {
        mData.clear();
        RetrofitManager.gankApi().getHistoryDate().enqueue(new Callback<RHistoyDate>() {
            @Override
            public void onResponse(Call<RHistoyDate> call, Response<RHistoyDate> response) {
                List<String> results = response.body().getResults();
                int size = results.size();
                LogU.i("HistoryPresenter -->", "历史日期字符串个数：" + size);
                for (int i = f; i < t; i++) {
                    LogU.i("HistoryPresenter -->", "进入for循环查询");
                    String s = results.get(i);
                    final HistoryData data = new HistoryData();
                    data.setDate(s);
                    final String date = s.replace("-", "/");
                    RetrofitManager.gankApi().getHistoryDayInfo(date).enqueue(new Callback<RHistoryDayInfo>() {
                        @Override
                        public void onResponse(Call<RHistoryDayInfo> call, Response<RHistoryDayInfo> response) {
                            RHistoryDayInfo body = response.body();
                            List<RHistoryDayInfo.ResultsBean> beans = body.getResults();
                            String title = beans.get(0).getTitle();
                            if (!TextUtils.isEmpty(title)) {
                                data.setTitle(title);
                            } else data.setTitle("今日干货");
                            RetrofitManager.gankApi().getDateInfo(date).enqueue(new Callback<RToday>() {
                                @Override
                                public void onResponse(Call<RToday> call, Response<RToday> response) {
                                    RToday body = response.body();
                                    if (body != null) {
                                        data.setTodaydata(body);
                                        List<GankInfo> belleList = body.getResults().belleList;
                                        if (!belleList.isEmpty()) {
                                            GankInfo gankInfo = belleList.get(0);
                                            String url = gankInfo.getUrl();
                                            LogU.i("HistoryPresenter -->", "添加图片URL地址：" + url);
                                            data.setImgurl(url);
                                        }
                                        mData.add(data);
                                        LogU.i("HistoryPresenter -->", "获取的数据个数：" + mData.size());
                                        if (mData.size() == 10) {
                                            //按日期排序
                                            Collections.sort(mData, new Comparator<HistoryData>() {
                                                @Override
                                                public int compare(HistoryData o1, HistoryData o2) {
                                                    long thisDate = DateUtil.getThisDate(o1.getDate());
                                                    long thisDate1 = DateUtil.getThisDate(o2.getDate());
                                                    long l = thisDate1 - thisDate;
                                                    if (l > 0) return 1;
                                                    else if (l == 0) return 0;
                                                    else return -1;
                                                }
                                            });
                                            mRefView.get().updateAdapter(f, mData);
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<RToday> call, Throwable t) {
                                    LogU.e("HistoryPresenter -->", "循环中获取出错");
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<RHistoryDayInfo> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<RHistoyDate> call, Throwable t) {

            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEventMessage(EventMessage msg) {
        switch (msg.getKey()) {

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
