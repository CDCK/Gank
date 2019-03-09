package com.cdck.androidplan.util;

import android.support.annotation.NonNull;

import com.cdck.androidplan.constnt.EventMessage;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xlk on 2018/11/30.
 */
public class NetworkUtil {

    /**
     * 通过HttpURLConnection实现一个get请求
     *
     * @param urlStr
     * @param params
     * @return
     */
    public static String doGet(String urlStr, HashMap<String, String> params) {
        String paramsStr = parseParams(params);
        //拿到Connection对象
        try {
            URL url = new URL(urlStr + "?" + paramsStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置GET请求方式
            conn.setRequestMethod("GET");
            //连接
//            conn.connect();
            //判断响应码
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                //得到响应流
                InputStream is = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                return reader.readLine();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 通过HttpURLConnection实现一个post请求
     *
     * @param urlStr
     * @param params
     * @return
     */
    public static String doPost(String urlStr, HashMap<String, String> params) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置POST请求
            conn.setRequestMethod("POST");
            //向服务器写数据（）写出去  params--->转换成paramsStr
            String paramsStr = parseParams(params);
            //因为开关默认是关闭的，所以要先设置启动开关
            conn.setDoOutput(true);//允许写出
            conn.getOutputStream().write(paramsStr.getBytes());
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                //判断请求是否成功
                InputStream is = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                return reader.readLine();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @param params
     * @return
     */
    @NonNull
    private static String parseParams(HashMap<String, String> params) {
        String paramsStr = "";
        if (params == null || params.isEmpty()) {
            return "";
        }
        for (Map.Entry<String, String> entry : params.entrySet()) {
            paramsStr += entry.getKey() + "=" + entry.getValue() + "&";
        }
        paramsStr.substring(0, paramsStr.length() - 1);
        return paramsStr;
    }

    //OKHttp 异步GET请求
    public static void okdoGet(final int key, final String url) {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.retryOnConnectionFailure();
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogU.e("NetworkUtil -->", "onFailure ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
//                LogU.i("NetworkUtil -->onResponse：", string);
                EventBus.getDefault().post(new EventMessage.Builder(key).value(string).build());
            }
        });
    }

    //OKHttp 同步GET请求
    public static void okTBdoGet(String url) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        final Call call = okHttpClient.newCall(request);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response execute = call.execute();
                    String s = execute.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
