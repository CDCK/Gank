package com.cdck.androidplan.model;

import com.cdck.androidplan.model.result.RHistoyDate;
import com.cdck.androidplan.model.result.GankInfos;
import com.cdck.androidplan.model.result.RHistoryDayInfo;
import com.cdck.androidplan.model.result.RToday;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Path;

/**
 * Created by xlk on 2019/3/1.
 */
public interface GankApi {

    /**
     * @param catalogue 类别
     * @param page      第几页
     * @return
     * @GET注解的作用:采用Get方法发送网络请求 获取指定类别中的数据 eg: http://gank.io/api/data/Android/10/1
     */
    @GET(value = "data/{catalogue}/" + 10 + "/{page}")
    Call<GankInfos> getCatalogue(@Path("catalogue") String catalogue, @Path("page") int page);


    /**
     * @HTTP的作用：替换@GET、@POST、@PUT、@DELETE、@HEAD注解的作用及更多功能拓展，可以直接使用上一种的方式 获取指定日期发布的数据
     * method：  网络请求的方法（区分大小写）
     * path：    网络请求地址路径
     * hasBody： 是否有请求体
     */
    @HTTP(method = "GET", path = "day/{year}/{month}/{day}", hasBody = false)
    Call<RToday> getDateInfo(@Path("year") int year, @Path("month") int month, @Path("day") int day);

    /**
     * 获取指定日期的数据
     *
     * @param date 日期 eg: "2015/02/11"
     * @return
     */
    @HTTP(method = "GET", path = "day/{date}", hasBody = false)
    Call<RToday> getDateInfo(@Path("date") String date);

    @GET("today")
    Call<RToday> getDateInfo();

    /**
     * 获取所有历史发布的日期
     *
     * @return
     */
    @GET("day/history")
    Call<RHistoyDate> getHistoryDate();

    /**
     * 获取指定日期的历史数据
     * @param date
     * @return
     */
    @GET("history/content/day/{date}")
    Call<RHistoryDayInfo> getHistoryDayInfo(@Path("date") String date);
}
