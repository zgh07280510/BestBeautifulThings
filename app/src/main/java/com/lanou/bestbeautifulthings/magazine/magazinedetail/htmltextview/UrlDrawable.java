package com.lanou.bestbeautifulthings.magazine.magazinedetail.htmltextview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by zouguohua on 16/7/29.
 */
public class UrlDrawable extends BitmapDrawable {
            public Bitmap bitmap;

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (bitmap!=null){
            canvas.drawBitmap(bitmap,0,0,getPaint());
        }
    }
}
