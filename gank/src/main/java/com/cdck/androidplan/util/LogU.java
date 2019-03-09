package com.cdck.androidplan.util;

import android.util.Log;

/**
 * Created by xlk on 2019/1/21.
 */
public class LogU {

    public static void e(String tag, Object str) {
        Log.e("LogU-->" + tag, str + "");
    }

    public static void i(String tag, Object str) {
        Log.i("LogU-->" + tag, str + "");
    }

    public static void d(String tag, Object str) {
        Log.d("LogU-->" + tag, str + "");
    }
}
