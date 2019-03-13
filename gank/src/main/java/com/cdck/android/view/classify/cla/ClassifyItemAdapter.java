package com.cdck.android.view.classify.cla;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cdck.android.R;
import com.cdck.android.model.result.GankInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by xlk on 2019/3/1.
 */
public class ClassifyItemAdapter extends BaseQuickAdapter<GankInfo, BaseViewHolder> {

    private final RequestOptions options;

    public ClassifyItemAdapter(int layoutResId, @Nullable List<GankInfo> data, Context context) {
        super(layoutResId, data);
        this.mContext = context;
        options = new RequestOptions()
                .placeholder(R.drawable.shape_white)//占位图
                .error(R.drawable.shape_white)//占位图
                .override(100)//指定图片大小，Target.SIZE_ORIGINAL 表示原图大小
                .skipMemoryCache(true)//是否禁用掉硬盘缓存
//                .dontAnimate()
        ;
    }

    @Override
    protected void convert(BaseViewHolder helper, GankInfo item) {
        String publishedAt = item.getPublishedAt();
        String date = publishedAt.substring(0, 10);
        helper.setText(R.id.item_table_desc, item.getDesc())
                .setText(R.id.item_table_author, item.getWho())
                .setText(R.id.item_table_date, date);
        helper.addOnClickListener(R.id.item_table_iv);
        ImageView view = helper.getView(R.id.item_table_iv);
        view.setImageResource(R.drawable.shape_white);
//        view.setBackgroundColor(Color.TRANSPARENT);
        //ImageView复用问题，判断当前没有图片URL数据时设置为透明
        boolean b = item.images == null || item.images.isEmpty();
        if (!b) {
            Glide.with(mContext.getApplicationContext())
//                        .asBitmap()//在load前面使用，表示如果是GIF也只展示第一帧的图片
//                .load(b ? R.drawable.shape_white : item.images.get(0))
                    .load(item.images.get(0))
                    .apply(options)//占位图和加载失败时显示的图片等配置
                    .into(view);
        }
    }
}
