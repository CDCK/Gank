package com.cdck.androidplan.view.activity.web;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.cdck.androidplan.R;
import com.cdck.androidplan.base.BaseActivity;
import com.cdck.androidplan.ui.TopBarUI;
import com.daimajia.numberprogressbar.NumberProgressBar;

import butterknife.BindView;

public class WebActivity extends BaseActivity<WebContract.Presenter> implements WebContract.View {

    @BindView(R.id.web_topbar)
    TopBarUI webTopbar;
    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.web_progressbar)
    NumberProgressBar progressbar;
    @BindView(R.id.constraint_layout)
    LinearLayout constraintLayout;
    public static final String BUNDLE_TITLE = "bundle_title";
    public static final String BUNDLE_URL = "bundle_url";

    /**
     * 所有进入该页面都是调用该方法
     *
     * @param from
     * @param bundle
     */
    public static void loadWebViewActivity(Context from, Bundle bundle) {
        Intent intent = new Intent(from, WebActivity.class);
        intent.putExtra("web_bundle", bundle);
        from.startActivity(intent);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_web;
    }

    @Override
    protected WebContract.Presenter initPresenter() {
        return new WebPresenter();
    }

    @Override
    protected void init() {
        Bundle bundle = getIntent().getBundleExtra("web_bundle");
        String title = bundle.getString(BUNDLE_TITLE);
        String webUrl = bundle.getString(BUNDLE_URL);
        mPresenter.setWebViewSettings(webView, webUrl);
        webTopbar.setTitle(title);
        webTopbar.setRightVisibility(false);
        webTopbar.setClickCallBack(new TopBarUI.onToolClickListener() {
            @Override
            public void clickTopBarLeft(int resid) {
                onBackPressed();
            }

            @Override
            public void clickTopBarRight(int resid) {

            }
        });
    }

    @Override
    public void showProgressBar(int progress) {
        if (progressbar == null) return;
        progressbar.setProgress(progress);
        if (progress == 100) {
            progressbar.setVisibility(View.GONE);
        } else {
            progressbar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setWebTitle(String title) {
        setTitle(title);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        if (webView != null) webView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (webView != null) webView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            constraintLayout.removeView(webView);
            webView.removeAllViews();
            webView.destroy();
            webView = null;
        }
    }
}
