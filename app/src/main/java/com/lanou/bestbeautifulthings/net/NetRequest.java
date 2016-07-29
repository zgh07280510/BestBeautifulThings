package com.lanou.bestbeautifulthings.net;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dllo on 16/7/26.
 */
public class NetRequest {

    private static NetRequest netRequest;
    public OkHttpClient client;
    private Gson gson;
    private final int SUCCESS = 1;
    private final int ERROR = -1;

    private NetRequest() {
        client = new OkHttpClient();
        gson = new Gson();
    }

    public static NetRequest getInstance() {
        if (netRequest == null) {
            synchronized (NetRequest.class) {
                if (netRequest == null) {
                    netRequest = new NetRequest();
                }
            }
        }
        return netRequest;
    }

    //异步的请求
    public <T> void getRequestAsync(String url,
                                    final Class<T> tClass,
                                    final NetListener.OnError onError,
                                    final NetListener.OnSucceed<T> onSucceed) {

        final Request request = new Request.Builder().url(url).build();
        final Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case SUCCESS:
                        onSucceed.OnSucceed((T) msg.obj);
                        break;
                    case ERROR:
                        onError.onError();
                        break;

                }
            }
        };
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onError.onError();
            }

            @Override
            public void onResponse(Call call, Response response) {
                T t = null;
                String result = null;


                try {
                    result = response.body().string();
                    t = gson.fromJson(result, tClass);
                    Message message = handler.obtainMessage();
                    message.what = SUCCESS;
                    message.obj = t;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                    handler.sendEmptyMessage(ERROR);
                }

            }
        });
    }

    //设计师页面数据
    public <T> void getDesignerBean(Class<T> tClass, NetListener.OnSucceed<T> onSucceed, NetListener.OnError onError) {
        getRequestAsync(Urls.DESIGNER, tClass, onError, onSucceed);
    }

    //画报页面数据
    public <T> void getMagazineBean(Class<T> tClass, NetListener.OnSucceed<T> onSucceed, NetListener.OnError onError){
        getRequestAsync(Urls.MAGAZINE,tClass,onError,onSucceed);
    }

    /**
     * 设计师简介及作品简介
     *
     * @param id      设计师页面数据designers内的每个id
     * @param tClass
     * @param onSucceed
     * @param onError
     * @param <T>
     */
    public <T> void getDesignerInformationBean(String id, Class<T> tClass, NetListener.OnSucceed<T> onSucceed, NetListener.OnError onError) {

        getRequestAsync(Urls.DESIGNER_INFORMATION_URL_HEAD + id + Urls.DESIGNER_INFORMATION_URL_END,tClass,onError, onSucceed);

    }

    /**
     * 设计师作品(recyclerView的数据)
     *
     * @param id      设计师页面数据designers内的每个id
     * @param t
     * @param onSucceed
     * @param onError
     * @param <T>
     */
    public <T> void getDesignerWorksBean(String id, Class<T> t, NetListener.OnSucceed<T> onSucceed, NetListener.OnError onError) {

        getRequestAsync(Urls.DESIGNER_WORKS_URL_HEAD + id + Urls.DESIGNER_WORKS_URL_END, t, onError, onSucceed);
        Log.d("NetRequest", Urls.DESIGNER_WORKS_URL_HEAD + id + Urls.DESIGNER_WORKS_URL_END);
    }


    /**
     * 设计师作品详情
     *
     * @param id      设计师作品数据的products内的每个id
     * @param t
     * @param onSucceed
     * @param onError
     * @param <T>
     */
    public <T> void getDesignerWorksInformationBean(String id, Class<T> t, NetListener.OnSucceed<T> onSucceed, NetListener.OnError onError) {

        getRequestAsync(Urls.DESIGNER_WORKS_INFORMATION_URL_HEAD + id + Urls.DESIGNER_WORKS_INFORMATION_URL_END, t, onError, onSucceed);

    }




}
