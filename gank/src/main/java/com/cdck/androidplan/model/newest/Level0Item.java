package com.cdck.androidplan.model.newest;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import static com.cdck.androidplan.view.newest.NewestAdapter.ITEM_TYPE_0;

/**
 * Created by xlk on 2019/1/17.
 */
public class Level0Item extends AbstractExpandableItem<Level1Item> implements MultiItemEntity {
    public String title;

    public Level0Item(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int getItemType() {
        return ITEM_TYPE_0;
    }

    @Override
    public int getLevel() {
        return ITEM_TYPE_0;
    }
}
