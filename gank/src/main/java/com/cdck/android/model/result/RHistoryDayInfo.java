package com.cdck.android.model.result;

import java.util.List;

/**
 * Created by xlk on 2019/3/4.
 * http://gank.io/api/history/content/day/2019/01/21
 * 某一天的内容数据
 */
public class RHistoryDayInfo extends RBase {

    private List<ResultsBean> results;

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean extends RContentBase {

    }
}
