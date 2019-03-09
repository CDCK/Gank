package com.cdck.androidplan.model.result;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by xlk on 2019/2/27.
 */
public class RToday extends RBase{

    private List<String> category;
    private Result results;

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public Result getResults() {
        return results;
    }

    public void setResults(Result results) {
        this.results = results;
    }

    public class Result{
        @SerializedName("Android") public List<GankInfo> androidList;
        @SerializedName("休息视频") public List<GankInfo> restList;
        @SerializedName("iOS") public List<GankInfo> iOSList;
        @SerializedName("福利") public List<GankInfo> belleList;
        @SerializedName("拓展资源") public List<GankInfo> expandList;
        @SerializedName("瞎推荐") public List<GankInfo> recommendList;
        @SerializedName("App") public List<GankInfo> appList;
        @SerializedName("前端") public List<GankInfo> frontList;

    }

    @Override
    public String toString() {
        return "RToday{" +
                "category=" + category +
                ", results=" + results +
                '}';
    }
}
