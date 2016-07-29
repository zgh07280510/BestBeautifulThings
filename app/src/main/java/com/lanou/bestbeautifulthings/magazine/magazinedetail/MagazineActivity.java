package com.lanou.bestbeautifulthings.magazine.magazinedetail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcelable;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.base.BaseActivity;
import com.lanou.bestbeautifulthings.magazine.Datum;
import com.lanou.bestbeautifulthings.util.RoundDrawable;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zouguohua on 16/7/27.
 */
public class MagazineActivity extends BaseActivity {

    float x1 = 0;
    float x2 = 0;
    float y1 = 0;
    float y2 = 0;
    private RelativeLayout titleBarRv;
    private ImageView bottomIconIv;
    private ImageView titleIconIv;
    private List<Datum> datas;
    private TextView contentTv;

    @Override
    public int setLayout() {
        return R.layout.activity_magazinedetail;
    }

    @Override
    protected void initView() {
        titleBarRv = (RelativeLayout) findViewById(R.id.relative_layout_title_bar);
        bottomIconIv = (ImageView) findViewById(R.id.bottom_bar_icon);
        titleIconIv = (ImageView) findViewById(R.id.magazine_detail_titlebar_icon);
        contentTv = (TextView) findViewById(R.id.magazine_detail_tv);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        Datum magBean = intent.getParcelableExtra("magBean");
        // Datum datum = (Datum) intent.getSerializableExtra("magBean");
        // Toast.makeText(this, datum.getHeaderTitle(), Toast.LENGTH_SHORT).show();
        //设置底部icon圆形图片
        setbottomIcon();
        //设置用户头像圆形图片
        settitleIcon();
        contentTv.setMovementMethod(ScrollingMovementMethod.getInstance());
        contentTv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //继承了Activity的onTouchEvent方法，直接监听点击事件
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //当手指按下的时候
                    x1 = event.getX();
                    y1 = event.getY();
                }
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    //当手指离开的时候
                    x2 = event.getX();
                    y2 = event.getY();
                    //向上滑
                    if (y1 - y2 > 50) {
                        showUpTranslateAnim();
                        titleBarRv.setVisibility(View.GONE);
                        //向下滑
                    } else if (y2 - y1 > 50) {
                        if (titleBarRv.getVisibility() == View.GONE) {
                            showDownTranslateAnim();
                            titleBarRv.setVisibility(View.VISIBLE);
                        }
                    }
                }
                return false;
            }
        });

    }

    private void settitleIcon() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.magazine_detail_icon);
        titleIconIv.setImageDrawable(new RoundDrawable(bitmap));
    }

    private void setbottomIcon() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_mine_portrait);
        bottomIconIv.setImageDrawable(new RoundDrawable(bitmap));
    }

    //向下平移动画
    private void showDownTranslateAnim() {
        TranslateAnimation translateAnimation
                = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_SELF, -1f,
                Animation.RELATIVE_TO_PARENT, 0
        );
        translateAnimation.setDuration(1000);
        titleBarRv.startAnimation(translateAnimation);
    }

    //向上平移动画
    private void showUpTranslateAnim() {
        TranslateAnimation translateAnimation
                = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_PARENT, -1f
        );
        translateAnimation.setDuration(1000);
        titleBarRv.startAnimation(translateAnimation);
    }


}
