package com.cdck.androidplan.view.newest;

import com.cdck.androidplan.base.BaseContract;
import com.cdck.androidplan.model.result.NewestSection;
import java.util.List;

/**
 * Created by xlk on 2019/1/17.
 * 最新页面 契约类
 */
public interface NewestContract extends BaseContract{
    interface Presenter extends BasePresenter<View> {
        /**
         * 获取某一天的数据
         * @param date eg: 2015/2/8
         */
        void getNewestData(String date);
    }

    interface View extends BaseView {

        void  refresh();//下拉刷新

        void updateSectionData(List<NewestSection> datas);
    }
}
