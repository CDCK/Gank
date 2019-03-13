package com.cdck.android.view.newest;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cdck.android.R;
import com.cdck.android.model.result.NewestSection;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by xlk on 2019/3/6.
 */
public class NewestSectionAdapter extends BaseSectionQuickAdapter<NewestSection, BaseViewHolder> {
    private final RequestOptions options;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public NewestSectionAdapter(int layoutResId, int sectionHeadResId, List<NewestSection> data) {
        super(layoutResId, sectionHeadResId, data);
        options = new RequestOptions()
                .placeholder(R.drawable.shape_white)//占位图
                .override(200)//指定图片大小，Target.SIZE_ORIGINAL 表示原图大小
//                .skipMemoryCache(true)//禁用掉硬盘缓存
//                .dontAnimate()
        ;
    }

    @Override
    protected void convertHead(BaseViewHolder helper, NewestSection item) {
        helper.setText(R.id.header_title_tv, item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewestSection item) {
        String publishedAt = item.t.getPublishedAt();
        String date = publishedAt.substring(0, 10);
        helper.setText(R.id.tv_title, item.t.getDesc())
                .setText(R.id.tv_author, item.t.getWho())
                .setText(R.id.tv_date, date);
        helper.addOnClickListener(R.id.iv_section);
        ImageView view = helper.getView(R.id.iv_section);
        Drawable drawable = mContext.getResources().getDrawable(R.drawable.shape_white);
        view.setBackgroundColor(Color.TRANSPARENT);
        //ImageView复用问题，判断当前没有图片URL数据时设置为透明
        boolean b = item.t.images == null || item.t.images.isEmpty();
        Glide.with(mContext.getApplicationContext())
//                        .asBitmap()//在load前面使用，表示如果是GIF也只展示第一帧的图片
                .load(b ? drawable : item.t.images.get(0))
                .apply(options)//占位图和加载失败时显示的图片等配置
                .into(view);
    }
}
