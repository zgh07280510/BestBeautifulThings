package com.lanou.bestbeautifulthings.base;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import cn.bmob.v3.Bmob;

/**
 * Created by dllo on 16/7/25.
 */
public class MyApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Bmob.initialize(this, "bf911a696fcf6c0d7321f0ad0912faff");
    }

    public static Context getContext() {
        return context;
    }
}
