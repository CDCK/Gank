package com.cdck.android.view.activity.main;


import com.cdck.android.base.BaseContract;
import com.cdck.android.model.result.RHistoyDate;

/**
 * Created by xlk on 2019/1/17.
 */
public interface MainContract extends BaseContract{
    interface Presenter extends BasePresenter<View> {
        void getHistory();
    }
    interface View extends BaseView {

        void showHistoryDate(RHistoyDate histoyDateInfo);

    }

}
