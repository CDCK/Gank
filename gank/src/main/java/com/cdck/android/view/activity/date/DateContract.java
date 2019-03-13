package com.cdck.android.view.activity.date;

import com.cdck.android.base.BaseContract;
import com.cdck.android.model.result.NewestSection;

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
