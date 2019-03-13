package com.cdck.android.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xlk on 2019/1/17.
 */
public abstract class BaseActivity<T extends BaseContract.BasePresenter> extends AppCompatActivity implements BaseContract.BaseView {
    private Unbinder mUnbinder;
    public T mPresenter;

    protected abstract int getContentViewLayoutID();

    /**
     * 创建prensenter
     *
     * @return <T extends BaseContract.BasePresenter> 必须是BaseContract.BasePresenter的子类
     */
    protected abstract T initPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        }
        setContentView(getContentViewLayoutID());
        getWindow().setEnterTransition(new Slide().setDuration(500));
        getWindow().setExitTransition(new Slide().setDuration(500));
        mUnbinder = ButterKnife.bind(this);
        mPresenter = initPresenter();
        mPresenter.attachView(this);
        init();
    }

    protected abstract void init();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }


    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void jumpTo(Class clz, Bundle bundle) {
        Intent intent = new Intent(this, clz);
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }
}
