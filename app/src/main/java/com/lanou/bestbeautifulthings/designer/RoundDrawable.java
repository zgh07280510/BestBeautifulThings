package com.lanou.bestbeautifulthings.designer;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/**
 * Created by dllo on 16/7/26.
 */
public class RoundDrawable extends Drawable {
    private Bitmap bitmap;
    private Paint mPaint;
    private RectF rectF;
    private int x, y;
    //构造方法

    public RoundDrawable(Bitmap bitmap) {
        this.bitmap = bitmap;
        mPaint = new Paint();
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(bitmapShader);
        mPaint.setAntiAlias(true);
    }

    @Override
    public void draw(Canvas canvas) {
        //画圆
        int min = Math.min(x, y);
        canvas.drawCircle(min / 2, min / 2, min / 2, mPaint);

    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);

    }

    @Override
    public int getOpacity() {
        return 0;
    }
}
