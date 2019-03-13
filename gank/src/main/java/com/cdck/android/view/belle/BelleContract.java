package com.cdck.android.view.belle;

import com.cdck.android.base.BaseContract;
import com.cdck.android.model.result.GankInfo;

import java.util.List;

/**
 * Created by xlk on 2019/1/17.
 * 分类页面 契约接口
 */
public interface BelleContract extends BaseContract {

    interface Presenter extends BasePresenter<View> {
        void getBelleData(int page);
    }

    interface View extends BaseView {

        void refresh();//下拉刷新

        void update(List<GankInfo> gankInfos);
    }
}
