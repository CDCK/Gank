package com.cdck.androidplan.view.activity.web;

import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cdck.androidplan.base.BasePresenter;

import java.lang.ref.Reference;

/**
 * Created by xlk on 2019/3/5.
 */
public class WebPresenter extends BasePresenter<WebContract.View> implements WebContract.Presenter {

    @Override
    public void setWebViewSettings(WebView webView, String url) {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        webView.setWebChromeClient(new ChromeClient());
        webView.setWebViewClient(new GankClient());
        webView.loadUrl(url);
    }

    private class ChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            Reference<WebContract.View> mRefView = WebPresenter.this.mRefView;
            if (mRefView != null) {
                WebContract.View view1 = mRefView.get();
                if (view1 != null) {
                    view1.showProgressBar(newProgress);
                }
            }
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (mRefView != null) {
                WebContract.View view1 = mRefView.get();
                if (view1 != null) {
                    view1.setWebTitle(title);
                }
            }
        }
    }

    private class GankClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url != null) view.loadUrl(url);
            return true;
        }
    }
}
