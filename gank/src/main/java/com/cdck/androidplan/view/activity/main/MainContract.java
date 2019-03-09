package com.cdck.androidplan.view.activity.main;


import com.cdck.androidplan.base.BaseContract;
import com.cdck.androidplan.model.result.RHistoyDate;

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
