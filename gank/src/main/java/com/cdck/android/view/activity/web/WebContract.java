package com.cdck.android.view.activity.web;

import android.webkit.WebView;

import com.cdck.android.base.BaseContract;

/**
 * Created by xlk on 2019/3/5.
 */
public interface WebContract extends BaseContract {
    interface Presenter extends BasePresenter<View> {
        void setWebViewSettings(WebView webView, String url);
    }

    interface View extends BaseView {
        void showProgressBar(int progress);

        void setWebTitle(String title);
    }
}
