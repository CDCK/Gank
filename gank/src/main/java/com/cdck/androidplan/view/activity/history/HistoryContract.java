package com.cdck.androidplan.view.activity.history;

import com.cdck.androidplan.base.BaseContract;
import com.cdck.androidplan.model.result.HistoryData;

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
