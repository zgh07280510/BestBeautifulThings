package com.lanou.bestbeautifulthings.discover.discoverdetail;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.base.BaseActivity;
import com.lanou.bestbeautifulthings.base.MyApp;
import com.lanou.bestbeautifulthings.discover.beans.ShoesBean;
import com.lanou.bestbeautifulthings.net.NetListener;
import com.lanou.bestbeautifulthings.net.NetRequest;
import com.lanou.bestbeautifulthings.util.XCRoundImageView;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;

/**
 * Created by dllo on 16/7/31.
 */
public class DiscoverDetailActivity extends BaseActivity {
    private Banner banner;
    private DetailBean bean;
    private TextView digestTv, userNameTv, labelTv, conceptTv, detailNameTv, descTv, titleTv, subTv;
    private ImageView titleIv;
    private XCRoundImageView userIv;
    private NoScrollGridView listView;
    private DetailActivityAdapter adapter;
    private String id = "693";
    private RelativeLayout relativeLayout;
    private DetailactivityTitleAdapter tAdapter;
    private NoScrollGridView mListView;

    @Override
    public int setLayout() {
        return R.layout.activity_detail_discover;
    }

    @Override
    protected void initView() {
        banner = (Banner) findViewById(R.id.discover_detail_banner);
        digestTv = (TextView) findViewById(R.id.detail_digest);
        userNameTv = (TextView) findViewById(R.id.detail_user_name);
        labelTv = (TextView) findViewById(R.id.detail_user_label);
        conceptTv = (TextView) findViewById(R.id.detail_user_concept);
        detailNameTv = (TextView) findViewById(R.id.detail_name);
        descTv = (TextView) findViewById(R.id.detail_desc);
        titleTv = (TextView) findViewById(R.id.detail_title_tv);
        subTv = (TextView) findViewById(R.id.detail_sub_title);
        titleIv = (ImageView) findViewById(R.id.detail_title_image);
        userIv = (XCRoundImageView) findViewById(R.id.detail_user_image);
        listView = (NoScrollGridView) findViewById(R.id.detail_list_view);
        relativeLayout = (RelativeLayout) findViewById(R.id.magazine_relayout);
        mListView = (NoScrollGridView) findViewById(R.id.magazine_list_view);

    }

    @Override
    protected void initData() {
        adapter = new DetailActivityAdapter(this);
        tAdapter = new DetailactivityTitleAdapter(this);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        NetRequest.getInstance().getDiscoverDtailInformation(110, id, DetailBean.class, new NetListener.OnSucceed<DetailBean>() {
            @Override
            public void OnSucceed(DetailBean result) {
                bean = result;
                banner.setImages(bean.getData().getCover_images());
                banner.setDelayTime(3000);
                banner.setBannerStyle(Banner.ACCESSIBILITY_LIVE_REGION_ASSERTIVE);
                digestTv.setText(bean.getData().getDigest());
                userNameTv.setText(bean.getData().getDesigner().getName());
                labelTv.setText(bean.getData().getDesigner().getLabel());
                conceptTv.setText(bean.getData().getDesigner().getConcept());
                detailNameTv.setText(bean.getData().getName());
                descTv.setText(bean.getData().getDesc());
                Picasso.with(MyApp.getContext()).load(bean.getData().getDesigner().getAvatar_url()).into(userIv);
                adapter.setData(bean.getData().getImages());
                listView.setAdapter(adapter);
                tAdapter.setData(bean);
                mListView.setAdapter(tAdapter);


            }
        }, new NetListener.OnError() {
            @Override
            public void onError() {

            }
        });
    }
}
