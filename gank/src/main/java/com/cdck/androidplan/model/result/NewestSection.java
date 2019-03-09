package com.cdck.androidplan.model.result;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by xlk on 2019/3/6.
 */
public class NewestSection extends SectionEntity<GankInfo> {
    public NewestSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public NewestSection(GankInfo gankInfo) {
        super(gankInfo);
    }
}
