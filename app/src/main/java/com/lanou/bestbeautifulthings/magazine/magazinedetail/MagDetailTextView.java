package com.lanou.bestbeautifulthings.magazine.magazinedetail;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by zouguohua on 16/7/28.
 */
public class MagDetailTextView extends TextView {
    public MagDetailTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MagDetailTextView(Context context) {
        super(context);
    }

    public MagDetailTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    static  private  String  converStreamToString(InputStream inputStream){
//        Scanner scanner=new Scanner(inputStream).useDelimiter()
        return  null;
    }
}
