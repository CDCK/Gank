package com.cdck.androidplan.model.result;


/**
 * Created by xlk on 2019/3/4.
 */
public class HistoryData {
    String title;
    String date;
    String imgurl;
    RToday todaydata;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public RToday getTodaydata() {
        return todaydata;
    }

    public void setTodaydata(RToday todaydata) {
        this.todaydata = todaydata;
    }

    @Override
    public String toString() {
        return "HistoryData{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", todaydata=" + todaydata +
                '}';
    }
}
