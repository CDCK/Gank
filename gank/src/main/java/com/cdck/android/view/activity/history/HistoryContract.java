package com.cdck.android.view.activity.history;

import com.cdck.android.base.BaseContract;
import com.cdck.android.model.result.HistoryData;

import java.util.List;

/**
 * Created by xlk on 2019/2/26.
 */
public interface HistoryContract extends BaseContract{

    interface Presenter extends BasePresenter<View> {

        void getHistoryDate(int f,int t);

    }

    interface View extends BaseView {

        void updateAdapter(int f, List<HistoryData> historyInfo);
    }
}
