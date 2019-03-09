package com.cdck.androidplan.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cdck.androidplan.R;


/**
 * Created by xlk on 2019/2/15.
 */
public class TopBarUI extends LinearLayout {

    private final int left_iv;
    private final int right_iv;
    private final String text;
    private final int textColor;
    private ImageButton left_ib;
    private ImageButton right_ib;
    private TextView center_tv;
    private onToolClickListener toollistener;


    public TopBarUI(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        left_iv = array.getResourceId(R.styleable.TopBar_left_img, R.drawable.topbar_back);
        right_iv = array.getResourceId(R.styleable.TopBar_right_img, R.drawable.topbar_search);
        text = array.getString(R.styleable.TopBar_centre_tv);
        textColor = array.getColor(R.styleable.TopBar_text_color, Color.WHITE);
        init();
    }

    private void init() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.topbar_layout, this, true);
        left_ib = inflate.findViewById(R.id.left_ib);
        right_ib = inflate.findViewById(R.id.right_ib);
        center_tv = inflate.findViewById(R.id.center_tv);
        left_ib.setImageResource(left_iv);
        left_ib.setTag(left_iv);
        right_ib.setImageResource(right_iv);
        right_ib.setTag(right_iv);
        center_tv.setText(text);
        center_tv.setTextColor(textColor);
        left_ib.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                toollistener.clickTopBarLeft((Integer) left_ib.getTag());
            }
        });
        right_ib.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                toollistener.clickTopBarRight((Integer) right_ib.getTag());
            }
        });
    }

    public void setTitle(String title) {
        center_tv.setText(title);
    }

    public void setRightIcon(int rightRes) {
        right_ib.setImageResource(rightRes);
        right_ib.setTag(rightRes);
    }

    public void setLeftIcon(int leftRes) {
        left_ib.setImageResource(leftRes);
        left_ib.setTag(leftRes);
    }

    public void setLeftVisibility(boolean flag) {
        left_ib.setVisibility(flag ? VISIBLE : GONE);
    }

    public void setRightVisibility(boolean flag) {
        right_ib.setVisibility(flag ? VISIBLE : GONE);
    }

    public interface onToolClickListener {
        void clickTopBarLeft(int resid);

        void clickTopBarRight(int resid);
    }

    public void setClickCallBack(onToolClickListener listener) {
        this.toollistener = listener;
    }

}
