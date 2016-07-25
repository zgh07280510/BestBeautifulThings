package com.lanou.bestbeautifulthings;

import android.content.Intent;
import android.os.CountDownTimer;
import android.widget.ImageView;

import com.lanou.bestbeautifulthings.base.BaseActivity;

/**
 * Created by dllo on 16/7/25.
 */
public class WelcomeActivity extends BaseActivity {
    private ImageView ivWelcome;
    private CountDownTimer countDownTimer;

    @Override
    public int setLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        ivWelcome = (ImageView) findViewById(R.id.iv_welcome);
    }

    @Override
    protected void initData() {
        countDownTimer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }
}
