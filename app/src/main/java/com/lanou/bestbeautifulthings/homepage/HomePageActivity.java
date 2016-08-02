package com.lanou.bestbeautifulthings.homepage;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.base.BaseActivity;
import com.lanou.bestbeautifulthings.designer.fragment.DesignerFragment;
import com.lanou.bestbeautifulthings.discover.discovermain.DiscoverMainFragment;
import com.lanou.bestbeautifulthings.magazine.MagazineFragment;
import com.lanou.bestbeautifulthings.mine.MineFragment;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by zouguohua on 16/7/25.
 */
public class HomePageActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    private RadioButton magazineRb, discoverRb, designerRb, mineRb;


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

    }

    @Override
    protected void initData() {
        magazineRb.setOnCheckedChangeListener(this);
        discoverRb.setOnCheckedChangeListener(this);
        designerRb.setOnCheckedChangeListener(this);
        mineRb.setOnCheckedChangeListener(this);

        magazineRb.setOnClickListener(this);
        discoverRb.setOnClickListener(this);
        designerRb.setOnClickListener(this);
        mineRb.setOnClickListener(this);

        magazineRb.setTextColor(Color.BLACK);

        //替换布局
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.homepage_framelayout, new MagazineFragment());
        fragmentTransaction.commit();



    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            buttonView.setTextColor(Color.BLACK);
        } else {
            buttonView.setTextColor(Color.GRAY);
        }

    }

    Fragment mfragment;
    MagazineFragment magazineFragment = new MagazineFragment();
    DiscoverMainFragment discoverFragment = new DiscoverMainFragment();
    DesignerFragment designerFragment = new DesignerFragment();
    MineFragment mineFragment = new MineFragment();

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (v.getId()) {
            case R.id.rb_magazine:
                fragmentTransaction.replace(R.id.homepage_framelayout, magazineFragment);
                mfragment = magazineFragment;


      

                break;
            case R.id.rb_discover:



                mfragment = discoverFragment;

                fragmentTransaction.replace(R.id.homepage_framelayout, new DiscoverMainFragment());

                break;
            case R.id.rb_designer:
                fragmentTransaction.replace(R.id.homepage_framelayout, designerFragment);
                mfragment = designerFragment;

                break;
            case R.id.rb_mine:
                fragmentTransaction.replace(R.id.homepage_framelayout, mineFragment);
                mfragment = mineFragment;
                break;

        }
        fragmentTransaction.commit();
    }
}