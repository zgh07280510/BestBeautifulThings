package com.lanou.bestbeautifulthings.splash;

import android.content.Intent;
import android.os.CountDownTimer;
import android.widget.ImageView;

import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.base.BaseActivity;
import com.lanou.bestbeautifulthings.homepage.HomePageActivity;

/**
 * Created by zouguohua on 16/7/25.
 */
public class SplashActivity extends BaseActivity {
    private ImageView ivWelcome;
    private CountDownTimer countDownTimer;

    @Override
    public int setLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        ivWelcome = (ImageView) findViewById(R.id.iv_splash);
    }

    @Override
    protected void initData() {
        countDownTimer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(SplashActivity.this, HomePageActivity.class);
                startActivity(intent);
                countDownTimer.cancel();
               finish();

            }
        }.start();
    }
}
