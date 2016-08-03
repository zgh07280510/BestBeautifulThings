package com.lanou.bestbeautifulthings.magazine.magazinedetail.htmltextview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

import java.lang.ref.SoftReference;

/**
 * Created by zouguohua on 16/7/29.
 */
public class UrlDrawable extends BitmapDrawable {
            public Bitmap bitmap;
    public SoftReference<Bitmap> bitmapSoftReference;

    public void setBitmapSoftReference(Bitmap bitmap) {
        //bitmapSoftReference = new SoftReference<Bitmap>(bitmap);
        this.bitmap = bitmap;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (bitmap!= null){
            Rect rect = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
            canvas.drawBitmap(bitmap,rect,getBounds(),getPaint());
        }
    }
}
