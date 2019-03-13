package com.cdck.android.view.belle;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.cdck.android.R;
import com.cdck.android.model.result.GankInfo;
import com.cdck.android.util.ScreenUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by xlk on 2019/3/8.
 */
public class BelleAdapter extends BaseQuickAdapter<GankInfo, BaseViewHolder> {
    private RequestOptions options;

    public BelleAdapter(int layoutResId, @Nullable List<GankInfo> data, Context context) {
        super(layoutResId, data);
        this.mContext = context;
        initImgScale(0, mData.size());
        options = new RequestOptions()
                .placeholder(R.drawable.shape_white)//占位图
                .error(R.drawable.error_load);
    }

    /**
     * 实现图片瀑布流效果，获取网络图片的宽高比
     *
     * @param from
     * @param to
     */
    public void initImgScale(int from, int to) {
        if (mData.size() < to) return;
        for (int i = from; i < to; i++) {
            final GankInfo gankInfo = mData.get(i);
            String url = gankInfo.getUrl();
            if (gankInfo.getScale() == 0) {
                Glide.with(mContext).load(url).into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        float scale = resource.getIntrinsicWidth() / (float) resource.getIntrinsicHeight();
                        gankInfo.setScale(scale);//设置该网络图片的宽高比例
                        notifyDataSetChanged();
                    }
                });
            } else {
                notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, GankInfo item) {
        ImageView view = helper.getView(R.id.item_belle_iv);
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = ScreenUtil.getScreenWidth(mContext) / 2 - ScreenUtil.dp2px(mContext, 4);
        if (item.getScale() != 0) {
            params.height = (int) (params.width / item.getScale());
        }
        Glide.with(mContext)
                .load(item.getUrl())
                .apply(options)
                .into(view);
    }
}
