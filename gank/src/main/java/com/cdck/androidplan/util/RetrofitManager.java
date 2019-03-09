package com.cdck.androidplan.util;

import com.cdck.androidplan.model.GankApi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xlk on 2019/3/1.
 */
public class RetrofitManager {

    private static OkHttpClient okHttpClient = null;
    private static Retrofit retrofit;
    private static String BASEURL = "http://gank.io/api/";
    private static RetrofitManager instance;

    public static GankApi gankApi(){
        return RetrofitManager.getInstance().createApi(GankApi.class);
    }

    public static RetrofitManager getInstance() {
        if (instance == null) {
            synchronized (RetrofitManager.class) {
                if (instance == null) {
                    instance = new RetrofitManager();
                }
            }
        }
        return instance;
    }

    public RetrofitManager() {
        init();
    }

    private void init() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        if(BuildConfig.DEBUG){
//            // Log信息拦截器
//            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//这里可以选择拦截级别
//            //设置 Debug Log 模式
//            builder.addInterceptor(loggingInterceptor);
//            //配置SSL证书检测
//            builder.sslSocketFactory(SSLSocketClient.getNoSSLSocketFactory());
//            builder.hostnameVerifier(SSLSocketClient.getHostnameVerifier());
//        }
        //错误重连
        builder.retryOnConnectionFailure(true);
        okHttpClient = builder.build();
    }

    public  <T> T createApi(Class<T> clz) {
        if (retrofit == null) {
            synchronized (RetrofitManager.class) {
                if (retrofit == null) {
                    Retrofit.Builder builder = new Retrofit.Builder();
                    builder.baseUrl(BASEURL)
                            .client(okHttpClient)
                            .addConverterFactory(GsonConverterFactory.create());
                    retrofit = builder.build();
                }
            }
        }
        return retrofit.create(clz);
    }
}
