package com.cdck.androidplan.view.belle;

import com.cdck.androidplan.base.BaseContract;
import com.cdck.androidplan.model.result.GankInfo;

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
