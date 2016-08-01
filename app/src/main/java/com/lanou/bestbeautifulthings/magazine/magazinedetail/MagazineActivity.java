package com.lanou.bestbeautifulthings.magazine.magazinedetail;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.base.BaseActivity;
import com.lanou.bestbeautifulthings.magazine.Datum;
import com.lanou.bestbeautifulthings.magazine.magazinedetail.htmltextview.HtmlTextView;
import com.lanou.bestbeautifulthings.util.RoundDrawable;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by zouguohua on 16/7/27.
 */
public class MagazineActivity extends BaseActivity implements View.OnClickListener {

    float x1 = 0;
    float x2 = 0;
    float y1 = 0;
    float y2 = 0;
    private RelativeLayout titleBarRv;
    private ImageView bottomIconIv;
    private ImageView titleIconIv;

    private HtmlTextView contentTv;
    private TextView mHeadTitleTv;
    private TextView mHeadAuthorTv;
    private ImageView mHeadImg;
    private ScrollView mMagazineScroll;
    private ImageView mBackIv;
    private RelativeLayout mDesignerRv;
    private ImageView mCommentIv;
    private ImageView shareIv;


    @Override
    public int setLayout() {
        return R.layout.activity_magazinedetail;
    }

    @Override
    protected void initView() {
        titleBarRv = (RelativeLayout) findViewById(R.id.relative_layout_title_bar);
        bottomIconIv = (ImageView) findViewById(R.id.bottom_bar_icon);
        titleIconIv = (ImageView) findViewById(R.id.magazine_detail_titlebar_icon);
        contentTv = (HtmlTextView) findViewById(R.id.magazine_detail_tv);
        mBackIv = (ImageView) findViewById(R.id.back_iv);
        mDesignerRv = (RelativeLayout) findViewById(R.id.designer_rv);
        mCommentIv = (ImageView) findViewById(R.id.comment_iv);
        shareIv = (ImageView) findViewById(R.id.magazine_detail_share);

        mMagazineScroll = (ScrollView) findViewById(R.id.magazine_detail_scrollview);

        //滑动的头布局
        mHeadTitleTv = (TextView) findViewById(R.id.magazine_head_title);
        mHeadAuthorTv = (TextView) findViewById(R.id.magazine_head_author);
        mHeadImg = (ImageView) findViewById(R.id.magazine_head_img);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void initData() {
        Intent intent = getIntent();
        Datum magBean = intent.getParcelableExtra("magBean");
        String content = magBean.getContent();
        String title = magBean.getHeaderTitle();//头标题
        String subTitle = magBean.getSub_title();
        String titleImg = magBean.getLink();
        //设置 图文混排
        contentTv.setHtmlFromString(content, false);


        //设置头标题
        mHeadTitleTv.setText(title);
        mHeadAuthorTv.setText(subTitle);
        Glide.with(this).load(titleImg).into(mHeadImg);

        //设置底部icon圆形图片
        setbottomIcon();
        //设置用户头像圆形图片
        settitleIcon();
        contentTv.setMovementMethod(ScrollingMovementMethod.getInstance());
        mMagazineScroll.setOnTouchListener(new View.OnTouchListener() {
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
                    if (y1 - y2 > 20) {
                        if (titleBarRv.getVisibility() != View.GONE) {
                            showUpTranslateAnim();
                            titleBarRv.setVisibility(View.GONE);
                        }
                        //向下滑
                    } else if (y2 - y1 > 20) {
                        if (titleBarRv.getVisibility() == View.GONE) {
                            showDownTranslateAnim();
                            titleBarRv.setVisibility(View.VISIBLE);
                        }
                    }
                }
                return false;
            }
        });

//        mMagazineScroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                if (oldScrollY - scrollY > 20) {
//                    if (titleBarRv.getVisibility() != View.GONE) {
//                        showUpTranslateAnim();
//                        titleBarRv.setVisibility(View.GONE);
//                    }
//                    //向下滑
//                } else if (scrollY - oldScrollY > 20) {
//                    if (titleBarRv.getVisibility() == View.GONE) {
//                        showDownTranslateAnim();
//                        titleBarRv.setVisibility(View.VISIBLE);
//                    }
//                }
//            }
//        });
        mBackIv.setOnClickListener(this);
        mDesignerRv.setOnClickListener(this);
        mCommentIv.setOnClickListener(this);
        shareIv.setOnClickListener(this);
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_iv:
                finish();
                break;
            case R.id.designer_rv:


                break;

            case R.id.comment_iv:


                break;

            case R.id.magazine_detail_share:
                ShareSDK.initSDK(this);
                OnekeyShare oks = new OnekeyShare();
                //关闭sso授权
                oks.disableSSOWhenAuthorize();

                // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
                //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
                // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
                //oks.setTitle(getString(R.string.share));
                // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
                oks.setTitleUrl("http://sharesdk.cn");
                // text是分享文本，所有平台都需要这个字段
                oks.setText("我是分享文本");
                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
                //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
                // url仅在微信（包括好友和朋友圈）中使用
                oks.setUrl("http://sharesdk.cn");
                // comment是我对这条分享的评论，仅在人人网和QQ空间使用
                oks.setComment("我是测试评论文本");
                // site是分享此内容的网站名称，仅在QQ空间使用
                oks.setSite(getString(R.string.app_name));
                // siteUrl是分享此内容的网站地址，仅在QQ空间使用
                oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
                oks.show(this);

                break;
        }
    }
}
