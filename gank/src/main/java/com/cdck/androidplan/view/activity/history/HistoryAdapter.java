package com.cdck.androidplan.view.activity.history;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.cdck.androidplan.R;
import com.cdck.androidplan.model.result.HistoryData;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by xlk on 2019/2/26.
 */
public class HistoryAdapter extends BaseQuickAdapter<HistoryData, BaseViewHolder> {

    private final RequestOptions options;

    public HistoryAdapter(int layoutResId, @Nullable List<HistoryData> data) {
        super(layoutResId, data);
        options = new RequestOptions()
                .placeholder(R.drawable.shape_white)//占位图
                .error(R.drawable.belee_img)
                .override(Target.SIZE_ORIGINAL)//指定图片大小，Target.SIZE_ORIGINAL 表示原图大小
                .skipMemoryCache(false)//是否禁用掉硬盘缓存
//                .dontAnimate()
        ;
    }

    @Override
    protected void convert(BaseViewHolder helper, HistoryData item) {
        helper.setText(R.id.item_history_date, item.getDate())
                .setText(R.id.item_history_title, item.getTitle() + "");
        ImageView view = helper.getView(R.id.item_history_belle);
        String imgurl = item.getImgurl();
        Glide.with(mContext)
                .load(imgurl != null ? imgurl : R.drawable.shape_white)
                .apply(options)//占位图和加载失败时显示的图片等配置
                .into(view);
    }
}
