package com.cdck.androidplan.base;

import com.cdck.androidplan.ui.TopBarValue;

/**
 * Created by xlk on 2019/2/28.
 */
public interface BaseContract {

    interface BasePresenter<T extends BaseView> {

        void attachView(T view);

        void detachView();

        void showToast(String msg);
    }

    interface BaseView {
        void showToast(String msg);

    }
}
