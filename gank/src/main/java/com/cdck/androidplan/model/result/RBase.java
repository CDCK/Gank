package com.cdck.androidplan.model.result;

import java.io.Serializable;

/**
 * Created by xlk on 2019/2/27.
 */
public class RBase implements Serializable {
    public boolean error;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "RBase{" +
                "error=" + error +
                '}';
    }
}
