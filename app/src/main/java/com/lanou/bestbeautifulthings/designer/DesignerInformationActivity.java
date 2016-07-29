package com.lanou.bestbeautifulthings.designer;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.base.BaseActivity;
import com.lanou.bestbeautifulthings.base.MyApp;
import com.lanou.bestbeautifulthings.designer.adapter.IntroduceFragmentAdapter;
import com.lanou.bestbeautifulthings.designer.adapter.IntroduceImagesAdapter;
import com.lanou.bestbeautifulthings.designer.bean.DesignerInformationBean;
import com.lanou.bestbeautifulthings.designer.fragment.BuyOnLineFragment;
import com.lanou.bestbeautifulthings.designer.fragment.FlagshipShopFragment;
import com.lanou.bestbeautifulthings.designer.fragment.IntroduceFragment;
import com.lanou.bestbeautifulthings.designer.fragment.PictorialFragment;
import com.lanou.bestbeautifulthings.net.NetListener;
import com.lanou.bestbeautifulthings.net.NetRequest;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/7/26.
 */
public class DesignerInformationActivity extends BaseActivity implements View.OnClickListener {
    private TabLayout tabLayout;
    private ViewPager viewPager, introduceViewPager;
    private TextView tvName, tvLabel, tvConcept, tvDescription;
    private Button btnBack;
    private ImageView ivDesignerHead;
    private IntroduceFragmentAdapter introduceFragmentAdapter;
    private IntroduceImagesAdapter introduceImagesAdapter;
    private List<Fragment> fragments;
    private DesignerInformationBean designerInformationBean;


    @Override
    public int setLayout() {

        return R.layout.activity_designer_detail;
    }

    @Override
    protected void initView() {
        viewPager = (ViewPager) findViewById(R.id.designer_information_viewPager);
        btnBack = (Button) findViewById(R.id.btn_designer_information_back);
        ivDesignerHead = (ImageView) findViewById(R.id.iv_designer_head);
        tvConcept = (TextView) findViewById(R.id.tv_concept);
        tvDescription = (TextView) findViewById(R.id.tv_description);
        tvLabel = (TextView) findViewById(R.id.tv_label);
        tvName = (TextView) findViewById(R.id.tv_designer_name);
        tabLayout = (TabLayout) findViewById(R.id.works_tabLayout);
        introduceViewPager = (ViewPager) findViewById(R.id.introduce_viewPager);


    }

    @Override
    protected void initData() {
        btnBack.setOnClickListener(this);
        initFragment();
        introduceFragmentAdapter = new IntroduceFragmentAdapter(getSupportFragmentManager());
        introduceFragmentAdapter.setFragments(fragments);
        introduceViewPager.setAdapter(introduceFragmentAdapter);
        tabLayout.setupWithViewPager(introduceViewPager);
        //  tabLayout.setTabTextColors(Color.TRANSPARENT);
        introduceImagesAdapter = new IntroduceImagesAdapter(MyApp.getContext());
        viewPager.setAdapter(introduceImagesAdapter);
        String id = String.valueOf(getIntent().getIntExtra("id", 0));
        NetRequest.getInstance().getDesignerInformationBean(id, DesignerInformationBean.class, new NetListener.OnSucceed<DesignerInformationBean>() {
            @Override
            public void OnSucceed(DesignerInformationBean result) {
                Glide.with(MyApp.getContext()).load(result.getData().getAvatar_url()).centerCrop().crossFade().into(ivDesignerHead);
                tvName.setText(result.getData().getName());
                tvLabel.setText(result.getData().getLabel());
                tvConcept.setText(result.getData().getConcept());
                tvDescription.setText(result.getData().getDescription());
                designerInformationBean = result;


            }
        }, new NetListener.OnError() {
            @Override
            public void onError() {

            }
        });
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        String id = String.valueOf(getIntent().getIntExtra("id", 0));
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        IntroduceFragment introduceFragment = new IntroduceFragment();
        introduceFragment.setArguments(bundle);
        fragments.add(introduceFragment);
        fragments.add(new FlagshipShopFragment());
        fragments.add(new PictorialFragment());
        fragments.add(new BuyOnLineFragment());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_designer_information_back:
                finish();
                break;
        }
    }


}
