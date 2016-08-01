package com.lanou.bestbeautifulthings.designer.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private List<Fragment> fragments;
    private DesignerInformationBean designerInformationBean;
    private ImageView[] imageViews;
    private ViewGroup viewGroup;
    private CheckBox cbDownUp;

    @Override
    public int setLayout() {
        return R.layout.activity_designer_detail;
    }

    @Override
    protected void initView() {
        viewPager = (ViewPager) findViewById(R.id.designer_information_viewPager);
        viewGroup = (ViewGroup) findViewById(R.id.image_viewGroup);
        btnBack = (Button) findViewById(R.id.btn_designer_information_back);
        ivDesignerHead = (ImageView) findViewById(R.id.iv_designer_head);
        tvConcept = (TextView) findViewById(R.id.tv_concept);
        tvDescription = (TextView) findViewById(R.id.tv_description);
        tvLabel = (TextView) findViewById(R.id.tv_label);
        tvName = (TextView) findViewById(R.id.tv_designer_name);
        tabLayout = (TabLayout) findViewById(R.id.works_tabLayout);
        introduceViewPager = (ViewPager) findViewById(R.id.introduce_viewPager);
        cbDownUp = (CheckBox) findViewById(R.id.cb_down_up);
    }

    @Override
    protected void initData() {
        btnBack.setOnClickListener(this);
        cbDownUp.setOnClickListener(this);
        initFragment();
        introduceFragmentAdapter = new IntroduceFragmentAdapter(getSupportFragmentManager());
        introduceFragmentAdapter.setFragments(fragments);
        introduceViewPager.setAdapter(introduceFragmentAdapter);
        tabLayout.setupWithViewPager(introduceViewPager);
        String id = String.valueOf(getIntent().getIntExtra("id", 0));
        NetRequest.getInstance().getDesignerInformationBean(id, DesignerInformationBean.class, new NetListener.OnSucceed<DesignerInformationBean>() {
            @Override
            public void OnSucceed(DesignerInformationBean result) {
                Glide.with(MyApp.getContext()).load(result.getData().getAvatar_url()).centerCrop().crossFade().into(ivDesignerHead);
                tvName.setText(result.getData().getName());
                tvLabel.setText(result.getData().getLabel());
                tvConcept.setText("“ "+result.getData().getConcept()+" ”");
                tvDescription.setText(result.getData().getDescription());
                designerInformationBean = result;
                IntroduceImagesAdapter introduceImagesAdapter = new IntroduceImagesAdapter(MyApp.getContext());
                introduceImagesAdapter.setDesignerInformationBean(designerInformationBean);
                viewPager.setAdapter(introduceImagesAdapter);
                //获取图片的数量
                imageViews = new ImageView[designerInformationBean.getData().getIntroduce_images().size()];
                //加点点
                for (int i = 0; i < imageViews.length; i++) {
                    ImageView image = new ImageView(MyApp.getContext());
                    if (i== 0){
                        image.setBackgroundResource(R.mipmap.abc_btn_rating_star_on_mtrl_alpha);
                    }else {
                        image.setBackgroundResource(R.mipmap.abc_btn_rating_star_off_mtrl_alpha);
                    }
                    imageViews[i] = image;
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(25,25));
                    viewGroup.addView(image,layoutParams);



                }
            }
        }, new NetListener.OnError() {
            @Override
            public void onError() {
            }
        });
        //调用实现滑动的方法
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setImageBackground(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    //设置轮播图点点
    public void setImageBackground(int items) {
        if (imageViews != null && imageViews.length > 0) {
            for (int i = 0; i < imageViews.length; i++) {
                if (i == items % imageViews.length) {
                    imageViews[i].setBackgroundResource(R.mipmap.abc_btn_rating_star_on_mtrl_alpha);
                } else {
                    imageViews[i].setBackgroundResource(R.mipmap.abc_btn_rating_star_off_mtrl_alpha);
                }
            }
        }
    }
    private void initFragment() {
        fragments = new ArrayList<>();
        String id = String.valueOf(getIntent().getIntExtra("id", 0));
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        IntroduceFragment introduceFragment = new IntroduceFragment();
        introduceFragment.setArguments(bundle);
        fragments.add(introduceFragment);
        PictorialFragment pictorialFragment = new PictorialFragment();
        FlagshipShopFragment flagshipShopFragment = new FlagshipShopFragment();
        BuyOnLineFragment buyOnLineFragment = new BuyOnLineFragment();
        pictorialFragment.setArguments(bundle);
        flagshipShopFragment.setArguments(bundle);
        buyOnLineFragment.setArguments(bundle);
        fragments.add(pictorialFragment);
        fragments.add(flagshipShopFragment);
        fragments.add(buyOnLineFragment);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_designer_information_back:
                finish();
                break;
            case R.id.cb_down_up:
             if (cbDownUp.isChecked() == false){
                 tvDescription.setMaxLines(2);
             }else {
                 tvDescription.setMaxLines(20);
             }
                break;
        }
    }
}
