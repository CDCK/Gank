package com.cdck.android.base;

/**
 * Created by xlk on 2019/2/28.
 */
public interface BaseContract {

    interface BasePresenter<T extends BaseView> {

        void attachView(T view);

        void detachView();

    }

    interface BaseView {
        void showToast(String msg);

    }
}
