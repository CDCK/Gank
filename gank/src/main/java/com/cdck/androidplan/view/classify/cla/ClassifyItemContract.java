package com.cdck.androidplan.view.classify.cla;

import com.cdck.androidplan.base.BaseContract;
import com.cdck.androidplan.model.result.GankInfo;

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
