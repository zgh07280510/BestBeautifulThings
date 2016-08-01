package com.lanou.bestbeautifulthings.magazine.magazinedetail.htmltextview;

import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lanou.bestbeautifulthings.base.MyApp;

/**
 * Created by zouguohua on 16/7/29.
 */
public class HtmlTextView extends TextView {
    public HtmlTextView(Context context) {
        super(context);
    }

    public HtmlTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HtmlTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setHtmlFromString(String html, boolean useLoclaDrawables) {
        Html.ImageGetter imageGetter;
        if (useLoclaDrawables) {
            imageGetter = new LocalImageGetter(MyApp.getContext());
        } else {
            imageGetter = new UrlImageGetter(this, MyApp.getContext());


        }

        setText(Html.fromHtml(html, imageGetter, new HtmlTagHandler()));

        setMovementMethod(LinkMovementMethod.getInstance());

    }
}
