package com.cdck.androidplan.model.result;

import java.util.List;

/**
 * Created by xlk on 2019/2/22.
 * 历史发布过的日期
 */
public class RHistoyDate extends RBase {

    private List<String> results;

    public List<String> getResults() {
        return results;
    }

    public void setResults(List<String> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "RHistoyDate{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }
}
