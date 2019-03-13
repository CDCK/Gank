package com.cdck.android.model.result;

import java.util.List;

/**
 * Created by xlk on 2019/3/2.
 * 指定类别中的数据
 */
public class GankInfos extends RBase{
    List<GankInfo> results;

    public List<GankInfo> getResults() {
        return results;
    }

    public void setResults(List<GankInfo> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "GankInfos{" +
                "results=" + results +
                '}';
    }
}
