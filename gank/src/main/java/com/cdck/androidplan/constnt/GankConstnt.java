package com.cdck.androidplan.constnt;

/**
 * Created by xlk on 2018/11/30.
 */
public class GankConstnt {
    //字符串后面有 / 表示要跟参数

    //一个有 https 一个是http://
    public static final String BASE_URL = "https://gank.io/api/";
//    public static final String BASE_URL = "http://gank.io/api";

    public static final String TODAY_URL = BASE_URL + "today";//获取最新数据

    public static final String HISTORY_DAY_URL = BASE_URL + "day/";//获取指定日期的数据 后面跟：2018/01/01

    public static final String HISTORY_DATE_URL = HISTORY_DAY_URL + "history";//获取历史日期

    public static final String HISTORY_CONTENT_URL = HISTORY_DATE_URL + "/content/";//获取某几日干货网站数据 后面跟 3/2 表示第二页的三个数据

    public static final String HISTORY_CONTENT_DAY_URL = HISTORY_CONTENT_URL + "day/";//获取特定日期网站数据: 后面跟：2018/01/01


    public static final String DATA_URL = BASE_URL + "data/";// 后面跟：数据类型/请求个数/第几页

    public static final String WELFARE_CONTENT_URL = DATA_URL + "福利/";//获取特定日期网站数据: 后面跟：请求个数/第几页


}
