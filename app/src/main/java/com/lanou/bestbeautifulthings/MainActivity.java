package com.lanou.bestbeautifulthings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;

import com.lanou.bestbeautifulthings.base.BaseActivity;

public class MainActivity extends BaseActivity {
    private RadioButton rbPic;

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        rbPic = (RadioButton) findViewById(R.id.rb_pic);
    }

    @Override
    protected void initData() {

    }
}
