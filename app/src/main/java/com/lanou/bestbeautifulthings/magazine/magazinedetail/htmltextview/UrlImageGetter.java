package com.lanou.bestbeautifulthings.magazine.magazinedetail.htmltextview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.lang.ref.SoftReference;

/**
 * Created by zouguohua on 16/7/28.
 */
public class UrlImageGetter implements Html.ImageGetter {
    private Context context;
    private TextView container;
    int width;

    public UrlImageGetter(TextView container, Context context) {
        this.container = container;
        this.context = context;
        //获取屏幕宽高
        width = context.getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    public Drawable getDrawable(String source) {
        final SoftReference<UrlDrawable> urlDrawableSoftReference = new SoftReference<UrlDrawable>(new UrlDrawable());
        // final UrlDrawable urlDrawable = new UrlDrawable();
        Glide.with(context).load(source).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                if (resource != null) {
                    resource.setConfig(Bitmap.Config.RGB_565);
                    //计算缩放比例 (屏幕宽/图片宽)
                    float scaleWidth = (float) width / resource.getWidth();
                    float temp = scaleWidth;
                    //取得想要缩放的matrix参数
                    Matrix matrix = new Matrix();
                    Log.d("UrlImageGetter", "scaleWidth:" + scaleWidth);
                    scaleWidth = Math.min(scaleWidth, 1.0f);
                    matrix.postScale(scaleWidth, scaleWidth);
                    resource = Bitmap.createBitmap(resource, 0, 0, resource.getWidth(), resource.getHeight(), matrix, true);
                    if (urlDrawableSoftReference.get() != null) {
                        urlDrawableSoftReference.get().setBitmapSoftReference(resource);
                        urlDrawableSoftReference.get().setBounds(0, 0, (int) (resource.getWidth() * temp), (int) (resource.getHeight() * temp));
                    }
                    resource = null;
                    container.setText(container.getText());
                }
            }
        });


        return urlDrawableSoftReference.get();
    }
}
