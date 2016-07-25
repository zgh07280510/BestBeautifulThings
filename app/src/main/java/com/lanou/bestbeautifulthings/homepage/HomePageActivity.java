package com.lanou.bestbeautifulthings.homepage;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.base.BaseActivity;

/**
 * Created by zouguohua on 16/7/25.
 */
public class HomePageActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {
    private RadioButton magazineRb, discoverRb, designerRb, mineRb;
    private RadioGroup homepageRadiogroup;

    @Override
    public int setLayout() {
        return R.layout.activity_homepage;
    }

    @Override
    protected void initView() {
        magazineRb = (RadioButton) findViewById(R.id.rb_magazine);
        discoverRb = (RadioButton) findViewById(R.id.rb_discover);
        designerRb = (RadioButton) findViewById(R.id.rb_designer);
        mineRb = (RadioButton) findViewById(R.id.rb_mine);
        homepageRadiogroup = (RadioGroup) findViewById(R.id.homepage_radiogroup);
    }

    @Override
    protected void initData() {
        magazineRb.setOnCheckedChangeListener(this);
        discoverRb.setOnCheckedChangeListener(this);
        designerRb.setOnCheckedChangeListener(this);
        mineRb.setOnCheckedChangeListener(this);

        magazineRb.setTextColor(Color.BLACK);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked){
            buttonView.setTextColor(Color.BLACK);
        }else {
            buttonView.setTextColor(Color.GRAY);
        }
    }
}