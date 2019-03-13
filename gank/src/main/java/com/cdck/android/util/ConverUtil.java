package com.cdck.android.util;

import com.cdck.android.model.result.GankInfo;
import com.cdck.android.model.result.NewestSection;
import com.cdck.android.model.result.RToday;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xlk on 2019/3/2.
 */
public class ConverUtil {

    public static List<NewestSection> converTo(RToday.Result result) {
        List<NewestSection> datas = new ArrayList<>();
        List<GankInfo> androidList = result.androidList;
        List<GankInfo> appList = result.appList;
        List<GankInfo> iOSList = result.iOSList;
        List<GankInfo> restList = result.restList;
        List<GankInfo> expandList = result.expandList;
        List<GankInfo> recommendList = result.recommendList;
        List<GankInfo> frontList = result.frontList;
        List<GankInfo> belleList = result.belleList;
        setData(datas, androidList);
        setData(datas, appList);
        setData(datas, iOSList);
        setData(datas, expandList);
        setData(datas, recommendList);
        setData(datas, frontList);
        setData(datas, restList);
        setData(datas, belleList);
        return datas;
    }

    private static void setData(List<NewestSection> datas, List<GankInfo> androidList) {
        if (androidList != null && !androidList.isEmpty()) {
            datas.add(new NewestSection(true, androidList.get(0).getType()));
            for (int i = 0; i < androidList.size(); i++) {
                datas.add(new NewestSection(androidList.get(i)));
            }
        }
    }
}
