package com.lanou.bestbeautifulthings.util;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/**
 * Created by Muguoqiang on 16/7/5.
 */
//将图片处理成圆角图片
public class RoundDrawable extends Drawable {
    private Bitmap bitmap;//要处理的花纹
    private Paint mPaint;
    private RectF mRectf;
    private int x, y;

    public RoundDrawable(Bitmap bitmap) {
        this.bitmap = bitmap;
        mPaint = new Paint();
        //画笔的属性(着色器) 设置画笔的花纹
        //当设置好了之后 ,用该画笔画任何东西,都是这张bitmap
        //例如画一个圆角矩形 就是这张的图片bitmap被处理成圆角矩形的样子
        //clamp是讲bitmap周围一圈像素 拉伸
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(bitmapShader);
        //抗锯齿模式
        mPaint.setAntiAlias(true);
    }

    //当imageview的宽高是wrap_content时来告诉Drawable它的宽高
    @Override
    public int getIntrinsicHeight() {
        return bitmap.getHeight();
    }

    @Override
    public int getIntrinsicWidth() {
        return bitmap.getWidth();
    }

    @Override//核心方法,可以将我们需要的内容绘制到屏幕上
    public void draw(Canvas canvas) {
        //画一个圆角矩形
        // canvas.drawRoundRect(mRectf, 100, 100, mPaint);
        //取最小值
        //画圆
        int min = Math.min(x, y);
        canvas.drawCircle(min / 2, min / 2, min / 2, mPaint);

    }

    @Override//该方法负责确定drawable的范围
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        mRectf = new RectF(left, top, right, bottom);
        x = right - left;
        y = bottom - top;
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
