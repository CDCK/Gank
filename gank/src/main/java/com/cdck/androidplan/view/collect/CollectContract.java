package com.cdck.androidplan.view.collect;

import com.cdck.androidplan.base.BaseContract;

/**
 * Created by xlk on 2019/1/17.
 * 分类页面 契约接口
 */
public interface CollectContract extends BaseContract {

    interface Presenter extends BasePresenter<View> {

    }

    interface View extends BaseView {

        void  refresh();//下拉刷新
    }
}
