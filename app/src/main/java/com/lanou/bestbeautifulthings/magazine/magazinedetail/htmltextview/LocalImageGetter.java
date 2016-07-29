package com.lanou.bestbeautifulthings.magazine.magazinedetail.htmltextview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;

/**
 * Created by zouguohua on 16/7/29.
 */
public class LocalImageGetter implements Html.ImageGetter {
    private Context context;

    public LocalImageGetter(Context context) {
        this.context = context;
    }

    @Override
    public Drawable getDrawable(String source) {
        return null;
    }
}
