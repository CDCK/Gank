package com.cdck.android.view.newest;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cdck.android.R;
import com.cdck.android.model.newest.Level0Item;
import com.cdck.android.model.newest.Level1Item;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by xlk on 2019/1/17.
 */
public class NewestAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    private final RequestOptions options;
    public static final int ITEM_TYPE_0 = 0;
    public static final int ITEM_TYPE_1 = 1;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public NewestAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(ITEM_TYPE_0, R.layout.item_newest_group_layout);
        addItemType(ITEM_TYPE_1, R.layout.item_newest_child_layout);
        options = new RequestOptions()
                .placeholder(R.drawable.shape_white)//占位图
                .override(200)//指定图片大小，Target.SIZE_ORIGINAL 表示原图大小
//                .skipMemoryCache(true)//禁用掉硬盘缓存
//                .dontAnimate()
        ;
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (item.getItemType()) {
            case ITEM_TYPE_0:
                Level0Item item0 = (Level0Item) item;
                helper.setText(R.id.item_newest_group_title, item0.getTitle());
                break;
            case ITEM_TYPE_1:
                Level1Item item1 = (Level1Item) item;
                String desc = item1.getDesc();
                helper.setText(R.id.item_newest_child_content_tv, desc)
                        .setText(R.id.item_newest_child_author_tv, item1.getWho())
                        .setText(R.id.item_newest_child_time_tv, item1.getCreatedAt())
                        .addOnClickListener(R.id.item_newest_child_pic_iv);
                ImageView view = helper.getView(R.id.item_newest_child_pic_iv);
                Drawable drawable = mContext.getResources().getDrawable(R.drawable.shape_white);
                view.setBackgroundColor(Color.TRANSPARENT);
                List<String> images = item1.getImages();
                //ImageView复用问题，判断当前没有图片URL数据时设置为透明
                boolean b = images == null || images.isEmpty();
                Glide.with(mContext.getApplicationContext())
//                        .asBitmap()//在load前面使用，表示如果是GIF也只展示第一帧的图片
                        .load(b ? drawable : images.get(0))
                        .apply(options)//占位图和加载失败时显示的图片等配置
                        .into(view);
                break;
        }
    }
}
