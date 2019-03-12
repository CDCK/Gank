package com.cdck.androidplan.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cdck.androidplan.R;
import com.cdck.androidplan.constnt.GankConstnt;
import com.cdck.androidplan.model.result.RHistoyDate;
import com.cdck.androidplan.util.DateUtil;
import com.cdck.androidplan.util.LogU;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xlk on 2019/2/22.
 */
public class NewestTopDateUI extends LinearLayout {

    private final String URL = "https://gank.io/api/day/";
    private ViewHolder holder;
    private final int day_color_f;
    private final int day_color_t;
    private final int week_color_f;
    private final int week_color_t;
    private final int daySize;
    private final int weekSize;
    private SelectListener listener;
    private List<Integer> ids;

    public NewestTopDateUI(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.NewestTopDateUI);
        day_color_f = array.getColor(R.styleable.NewestTopDateUI_day_color_f, context.getColor(R.color.newestTopDateUI_day_color_f));
        day_color_t = array.getColor(R.styleable.NewestTopDateUI_day_color_t, context.getColor(R.color.newestTopDateUI_day_color_t));
        week_color_f = array.getColor(R.styleable.NewestTopDateUI_week_color_f, context.getColor(R.color.newestTopDateUI_week_color_f));
        week_color_t = array.getColor(R.styleable.NewestTopDateUI_week_color_t, context.getColor(R.color.newestTopDateUI_week_color_t));
        daySize = array.getInteger(R.styleable.NewestTopDateUI_day_text_size, 20);
        weekSize = array.getInteger(R.styleable.NewestTopDateUI_week_text_size, 10);
        init();
    }

    public void update(RHistoyDate histoyDateInfo) {
        List<String> dates = histoyDateInfo.getResults();
        if (dates == null) return;
        LogU.i("NewestTopDateUI -->", "共有" + dates.size() + "条历史发布");
        for (int i = 0; i < 7; i++) {
            setText(i, DateUtil.stringToDate(dates.get(i), DateUtil.FORMAT_DATE));
        }
    }

    private void setText(int i, Date date) {
        switch (i) {
            case 0:
                holder.date_1.setTag(URL + DateUtil.getThisDate(date));
                holder.day_1.setText(DateUtil.getDayOfDate(date));
                holder.week_1.setText(DateUtil.getWeekOfDate(date));
                holder.month_1.setText(DateUtil.getMonthOfDate(date));
                break;
            case 1:
                holder.date_2.setTag(URL + DateUtil.getThisDate(date));
                holder.day_2.setText(DateUtil.getDayOfDate(date));
                holder.week_2.setText(DateUtil.getWeekOfDate(date));
                holder.month_2.setText(DateUtil.getMonthOfDate(date));
                break;
            case 2:
                holder.date_3.setTag(URL + DateUtil.getThisDate(date));
                holder.day_3.setText(DateUtil.getDayOfDate(date));
                holder.week_3.setText(DateUtil.getWeekOfDate(date));
                holder.month_3.setText(DateUtil.getMonthOfDate(date));
                break;
            case 3:
                holder.date_4.setTag(URL + DateUtil.getThisDate(date));
                holder.day_4.setText(DateUtil.getDayOfDate(date));
                holder.week_4.setText(DateUtil.getWeekOfDate(date));
                holder.month_4.setText(DateUtil.getMonthOfDate(date));
                break;
            case 4:
                holder.date_5.setTag(URL + DateUtil.getThisDate(date));
                holder.day_5.setText(DateUtil.getDayOfDate(date));
                holder.week_5.setText(DateUtil.getWeekOfDate(date));
                holder.month_5.setText(DateUtil.getMonthOfDate(date));
                break;
            case 5:
                holder.date_6.setTag(URL + DateUtil.getThisDate(date));
                holder.day_6.setText(DateUtil.getDayOfDate(date));
                holder.week_6.setText(DateUtil.getWeekOfDate(date));
                holder.month_6.setText(DateUtil.getMonthOfDate(date));
                break;
            case 6:
                holder.date_7.setTag(URL + DateUtil.getThisDate(date));
                holder.day_7.setText(DateUtil.getDayOfDate(date));
                holder.week_7.setText(DateUtil.getWeekOfDate(date));
                holder.month_7.setText(DateUtil.getMonthOfDate(date));
                break;
        }
    }

    private void init() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.newest_top_date_ui_layout, this, true);
        holder = new ViewHolder(inflate);
        holder.history_iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.jump2history();
                }
            }
        });
        ids = new ArrayList<>();
        ids.add(holder.date_1.getId());
        ids.add(holder.date_2.getId());
        ids.add(holder.date_3.getId());
        ids.add(holder.date_4.getId());
        ids.add(holder.date_5.getId());
        ids.add(holder.date_6.getId());
        ids.add(holder.date_7.getId());
        setDateClickListener(holder.date_1);
        setDateClickListener(holder.date_2);
        setDateClickListener(holder.date_3);
        setDateClickListener(holder.date_4);
        setDateClickListener(holder.date_5);
        setDateClickListener(holder.date_6);
        setDateClickListener(holder.date_7);
        setWeekStyle(holder.day_1, holder.week_1, holder.month_1);
        setWeekStyle(holder.day_2, holder.week_2, holder.month_2);
        setWeekStyle(holder.day_3, holder.week_3, holder.month_3);
        setWeekStyle(holder.day_4, holder.week_4, holder.month_4);
        setWeekStyle(holder.day_5, holder.week_5, holder.month_5);
        setWeekStyle(holder.day_6, holder.week_6, holder.month_6);
        setWeekStyle(holder.day_7, holder.week_7, holder.month_7);
    }

    private void setDateClickListener(final RelativeLayout date) {
        date.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                select(v.getId());
                if (listener != null) {
                    String url = (String) v.getTag();
                    listener.select(url);
                }
            }
        });
    }

    public void select(int id) {
        for (int i = 0; i < ids.size(); i++) {
            if (ids.get(i) == id) {
                setViewTextColor(true, ids.get(i));
            } else {
                setViewTextColor(false, ids.get(i));
            }
        }
    }

    public void setViewTextColor(boolean select, int id) {
        switch (id) {
            case R.id.date_1:
                holder.day_1.setTextColor(select ? day_color_t : day_color_f);
                holder.week_1.setTextColor(select ? week_color_t : week_color_f);
                holder.month_1.setTextColor(select ? week_color_t : week_color_f);
                break;
            case R.id.date_2:
                holder.day_2.setTextColor(select ? day_color_t : day_color_f);
                holder.week_2.setTextColor(select ? week_color_t : week_color_f);
                holder.month_2.setTextColor(select ? week_color_t : week_color_f);
                break;
            case R.id.date_3:
                holder.day_3.setTextColor(select ? day_color_t : day_color_f);
                holder.week_3.setTextColor(select ? week_color_t : week_color_f);
                holder.month_3.setTextColor(select ? week_color_t : week_color_f);
                break;
            case R.id.date_4:
                holder.day_4.setTextColor(select ? day_color_t : day_color_f);
                holder.week_4.setTextColor(select ? week_color_t : week_color_f);
                holder.month_4.setTextColor(select ? week_color_t : week_color_f);
                break;
            case R.id.date_5:
                holder.day_5.setTextColor(select ? day_color_t : day_color_f);
                holder.week_5.setTextColor(select ? week_color_t : week_color_f);
                holder.month_5.setTextColor(select ? week_color_t : week_color_f);
                break;
            case R.id.date_6:
                holder.day_6.setTextColor(select ? day_color_t : day_color_f);
                holder.week_6.setTextColor(select ? week_color_t : week_color_f);
                holder.month_6.setTextColor(select ? week_color_t : week_color_f);
                break;
            case R.id.date_7:
                holder.day_7.setTextColor(select ? day_color_t : day_color_f);
                holder.week_7.setTextColor(select ? week_color_t : week_color_f);
                holder.month_7.setTextColor(select ? week_color_t : week_color_f);
                break;
        }
    }

    private void setWeekStyle(TextView day, TextView week, TextView month) {
        day.setTextSize(daySize);
        week.setTextSize(weekSize);
        month.setTextSize(weekSize);
    }

    public static class ViewHolder {
        public View rootView;
        public TextView day_1;
        public TextView week_1;
        public TextView month_1;
        public TextView day_2;
        public TextView week_2;
        public TextView month_2;
        public TextView day_3;
        public TextView week_3;
        public TextView month_3;
        public TextView day_4;
        public TextView week_4;
        public TextView month_4;
        public TextView day_5;
        public TextView week_5;
        public TextView month_5;
        public TextView day_6;
        public TextView week_6;
        public TextView month_6;
        public TextView day_7;
        public TextView week_7;
        public TextView month_7;
        public RelativeLayout date_1;
        public RelativeLayout date_2;
        public RelativeLayout date_3;
        public RelativeLayout date_4;
        public RelativeLayout date_5;
        public RelativeLayout date_6;
        public RelativeLayout date_7;
        public ImageView history_iv;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.date_1 = (RelativeLayout) rootView.findViewById(R.id.date_1);
            this.date_2 = (RelativeLayout) rootView.findViewById(R.id.date_2);
            this.date_3 = (RelativeLayout) rootView.findViewById(R.id.date_3);
            this.date_4 = (RelativeLayout) rootView.findViewById(R.id.date_4);
            this.date_5 = (RelativeLayout) rootView.findViewById(R.id.date_5);
            this.date_6 = (RelativeLayout) rootView.findViewById(R.id.date_6);
            this.date_7 = (RelativeLayout) rootView.findViewById(R.id.date_7);
            this.day_1 = (TextView) rootView.findViewById(R.id.day_1);
            this.week_1 = (TextView) rootView.findViewById(R.id.week_1);
            this.month_1 = (TextView) rootView.findViewById(R.id.month_1);
            this.day_2 = (TextView) rootView.findViewById(R.id.day_2);
            this.week_2 = (TextView) rootView.findViewById(R.id.week_2);
            this.month_2 = (TextView) rootView.findViewById(R.id.month_2);
            this.day_3 = (TextView) rootView.findViewById(R.id.day_3);
            this.week_3 = (TextView) rootView.findViewById(R.id.week_3);
            this.month_3 = (TextView) rootView.findViewById(R.id.month_3);
            this.day_4 = (TextView) rootView.findViewById(R.id.day_4);
            this.week_4 = (TextView) rootView.findViewById(R.id.week_4);
            this.month_4 = (TextView) rootView.findViewById(R.id.month_4);
            this.day_5 = (TextView) rootView.findViewById(R.id.day_5);
            this.week_5 = (TextView) rootView.findViewById(R.id.week_5);
            this.month_5 = (TextView) rootView.findViewById(R.id.month_5);
            this.day_6 = (TextView) rootView.findViewById(R.id.day_6);
            this.week_6 = (TextView) rootView.findViewById(R.id.week_6);
            this.month_6 = (TextView) rootView.findViewById(R.id.month_6);
            this.day_7 = (TextView) rootView.findViewById(R.id.day_7);
            this.week_7 = (TextView) rootView.findViewById(R.id.week_7);
            this.month_7 = (TextView) rootView.findViewById(R.id.month_7);
            this.history_iv = (ImageView) rootView.findViewById(R.id.history_iv);
        }

    }

    public void setOnSelectListener(SelectListener listener) {
        this.listener = listener;
    }

    public interface SelectListener {
        void select(String url);

        void jump2history();
    }
}
