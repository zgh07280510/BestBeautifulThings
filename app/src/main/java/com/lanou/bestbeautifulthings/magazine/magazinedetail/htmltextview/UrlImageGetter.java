package com.lanou.bestbeautifulthings.magazine.magazinedetail.htmltextview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

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
        final UrlDrawable urlDrawable = new UrlDrawable();
        Glide.with(context).load(source).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                if (resource != null) {
                    //计算缩放比例 (屏幕宽/图片宽)
                    float scaleWidth = (float) width / resource.getWidth();
                    //取得想要缩放的matrix参数
                    Matrix matrix = new Matrix();
                    matrix.postScale(scaleWidth, scaleWidth);
                    resource = Bitmap.createBitmap(resource, 0, 0, resource.getWidth(), resource.getHeight(), matrix, true);
                    urlDrawable.bitmap = resource;
                    urlDrawable.setBounds(0, 0, resource.getWidth(), resource.getHeight());
                    container.setText(container.getText());



                }
            }
        });


        return urlDrawable;
    }
}
