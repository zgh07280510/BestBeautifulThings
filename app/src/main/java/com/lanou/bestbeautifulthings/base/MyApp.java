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
        Bmob.initialize(context, "17ebad504652156df5be753753517ac0");
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
