package com.cdck.androidplan.view.activity.date;

import com.cdck.androidplan.base.BaseContract;
import com.cdck.androidplan.model.result.NewestSection;

import java.util.List;

/**
 * Created by xlk on 2019/3/5.
 */
public interface DateContract extends BaseContract {

    interface Presenter extends BasePresenter<View> {
        void getDateInfo(String date);

    }

    interface View extends BaseView {

        void updateSectionData(List<NewestSection> datas);
    }
}
