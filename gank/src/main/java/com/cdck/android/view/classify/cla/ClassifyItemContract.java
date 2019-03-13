package com.cdck.android.view.classify.cla;

import com.cdck.android.base.BaseContract;
import com.cdck.android.model.result.GankInfo;

import java.util.List;

/**
 * Created by xlk on 2019/1/17.
 * 分类页面 契约接口
 */
public interface ClassifyItemContract extends BaseContract{

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getCurrentData(String type,int page);
    }

    interface View extends BaseView {
        void update(List<GankInfo> gankInfo);
    }
}
