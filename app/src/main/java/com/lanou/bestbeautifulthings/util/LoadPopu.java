package com.lanou.bestbeautifulthings.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.mine.UserInfo;



import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * Created by dllo on 16/8/2.
 */
public class LoadPopu {
    private static PopupWindow popupWindow;
    private static View view;
    private static LinearLayout sinaLayout;
    private static LinearLayout qqLayout;
    private static LinearLayout weixinLayout;
    private static Platform qq;
    private static Platform sina;


    public static void showLoadPopu(final Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.load_popu,null);
        sinaLayout = (LinearLayout) view.findViewById(R.id.sina_layout);
        sinaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sina = ShareSDK.getPlatform(SinaWeibo.NAME);
                sina.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        Intent intent = new Intent("sina load is success");
                        context.sendBroadcast(intent);
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                });
                sina.authorize();
                popupWindow.dismiss();

            }
        });
        qqLayout = (LinearLayout) view.findViewById(R.id.qq_layout);
        qqLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qq = ShareSDK.getPlatform(QQ.NAME);
                qq.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        Intent intent = new Intent("load is sucess");
                        context.sendBroadcast(intent);

                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                        Toast.makeText(context, "登录失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                        Toast.makeText(context, "取消登录", Toast.LENGTH_SHORT).show();

                    }
                });
                qq.authorize();
                popupWindow.dismiss();

            }
        });

        weixinLayout = (LinearLayout) view.findViewById(R.id.weichat_layout);
        weixinLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Platform weixin = ShareSDK.getPlatform(Wechat.NAME);
                weixin.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                });
                weixin.authorize();
            }
        });

        popupWindow = new PopupWindow(view, DisplayUtil.px2dip(context,4500), DisplayUtil.px2dip(context,5800), false) {
            @Override
            public void dismiss() {
                super.dismiss();


            }
        };
        //外部获得焦点
        popupWindow.setOutsideTouchable(true);
        //内部获得焦点
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setInputMethodMode(popupWindow.INPUT_METHOD_NEEDED);
        popupWindow.showAtLocation(view, Gravity.CENTER, Gravity.CENTER, DisplayUtil.px2dip(context,200));
    }
    public static UserInfo getQQUserInfo(){
        UserInfo userQQInfo = new UserInfo();
        userQQInfo.setUserQQId(qq.getDb().getUserId());
        userQQInfo.setUserQQImageUrl(qq.getDb().getUserIcon());
        userQQInfo.setUserQQName(qq.getDb().getUserName());
        return userQQInfo;
    }
    public static UserInfo getSinaUserInfo(){
        UserInfo userSinaInfo = new UserInfo();
        userSinaInfo.setUserSinaId(sina.getDb().getUserId());
        userSinaInfo.setUserSinaImagUrl(sina.getDb().getUserIcon());
        userSinaInfo.setUserSinaName(sina.getDb().getUserName());
        return userSinaInfo;

    }


}
