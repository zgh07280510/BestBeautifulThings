package com.lanou.bestbeautifulthings.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.mine.UserQQBean;
import com.lanou.bestbeautifulthings.mine.UserSinaBean;


import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;


/**
 * Created by dllo on 16/8/2.
 */
public class LoadPopu {
    private static PopupWindow popupWindow;
    private static View view;
    private static LinearLayout sinaLayout;
    private static LinearLayout qqLayout;
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
                        UserSinaBean userInfo = new UserSinaBean();
                        userInfo.setSinaUserIcon(sina.getDb().getUserIcon());
                        userInfo.setSinaUserName(sina.getDb().getUserName());
                        userInfo.setSinaUserId(sina.getDb().getUserId());
                        userInfo.save(context, new SaveListener() {
                            @Override
                            public void onSuccess() {
                                Log.d("LoadPopu", "插入数据成功");
                            }

                            @Override
                            public void onFailure(int i, String s) {

                            }
                        });
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                        Toast.makeText(context, "登录失败了", Toast.LENGTH_SHORT).show();

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
                        UserQQBean bean = new UserQQBean();
                        bean.setQQUserIcon(qq.getDb().getUserIcon());
                        bean.setQQUserId(qq.getDb().getUserName());
                        bean.setQQUserName(qq.getDb().getUserId());
                        bean.save(context, new SaveListener() {
                            @Override
                            public void onSuccess() {
                                Log.d("LoadPopu", "插入数据成功");
                            }

                            @Override
                            public void onFailure(int i, String s) {

                            }
                        });



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

        WindowManager wm = (WindowManager)context
                .getSystemService(Context.WINDOW_SERVICE);

        int width = (int) (wm.getDefaultDisplay().getWidth()*0.9);
        int height = (int) (wm.getDefaultDisplay().getHeight()*0.8);


        popupWindow = new PopupWindow(view, width, height, false) {

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



}
