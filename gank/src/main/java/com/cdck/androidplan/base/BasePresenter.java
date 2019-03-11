package com.cdck.androidplan.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by xlk on 2019/2/28.
 */
public class BasePresenter<V extends BaseContract.BaseView> implements BaseContract.BasePresenter<V> {


    protected Reference<V> mRefView;

    @Override
    public void attachView(V view) {
        mRefView = new WeakReference<>(view);
    }

    @Override
    public void detachView() {
        if (mRefView != null) {
            mRefView.clear();
            mRefView = null;
        }
    }

    protected V getView() {
        if (!isViewAttached()) throw new IllegalStateException("view 还未进行绑定！");
        return mRefView.get();
    }

    protected boolean isViewAttached() {
        return mRefView != null && mRefView.get() != null;
    }

    @Override
    public void showToast(String msg) {
        mRefView.get().showToast(msg);
    }

}
