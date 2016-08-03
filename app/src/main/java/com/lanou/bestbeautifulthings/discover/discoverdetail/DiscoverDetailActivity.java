package com.lanou.bestbeautifulthings.discover.discoverdetail;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.base.BaseActivity;
import com.lanou.bestbeautifulthings.base.MyApp;
import com.lanou.bestbeautifulthings.discover.beans.CommentBean;
import com.lanou.bestbeautifulthings.discover.beans.ShoesBean;
import com.lanou.bestbeautifulthings.magazine.magazinedetail.MagazineActivity;
import com.lanou.bestbeautifulthings.net.NetListener;
import com.lanou.bestbeautifulthings.net.NetRequest;
import com.lanou.bestbeautifulthings.util.XCRoundImageView;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by dllo on 16/7/31.
 */
public class DiscoverDetailActivity extends BaseActivity {
    private Banner banner;
    private DetailBean bean;
    private TextView digestTv, userNameTv, labelTv, conceptTv, detailNameTv, descTv;
    private XCRoundImageView userIv;
    private NoScrollGridView listView;
    private DetailActivityAdapter adapter;
    private String id = "693";
    private DetailactivityTitleAdapter tAdapter;
    private NoScrollGridView mListView;
    private LinearLayout commentLayout;
    private EditText editText;
    private String cId;
    private TextView countTv;


    @Override
    public int setLayout() {
        return R.layout.activity_detail_discover;
    }

    @Override
    protected void initView() {
        countTv = (TextView) findViewById(R.id.discover_comment_count);
        editText = (EditText) findViewById(R.id.discover_detail_et);
        commentLayout = (LinearLayout) findViewById(R.id.discover_detail_comment_layout);
        banner = (Banner) findViewById(R.id.discover_detail_banner);
        digestTv = (TextView) findViewById(R.id.detail_digest);
        userNameTv = (TextView) findViewById(R.id.detail_user_name);
        labelTv = (TextView) findViewById(R.id.detail_user_label);
        conceptTv = (TextView) findViewById(R.id.detail_user_concept);
        detailNameTv = (TextView) findViewById(R.id.detail_name);
        descTv = (TextView) findViewById(R.id.detail_desc);
        userIv = (XCRoundImageView) findViewById(R.id.detail_user_image);
        listView = (NoScrollGridView) findViewById(R.id.detail_list_view);
        mListView = (NoScrollGridView) findViewById(R.id.magazine_list_view);

    }

    @Override
    protected void initData() {
        adapter = new DetailActivityAdapter(this);
        tAdapter = new DetailactivityTitleAdapter(this);
        final Intent intent = getIntent();
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
                conceptTv.setText("“" + bean.getData().getDesigner().getConcept() + "”");
                detailNameTv.setText(bean.getData().getName());
                descTv.setText(bean.getData().getDesc());
                cId = String.valueOf(bean.getData().getDesigner().getId());
                Log.d("DiscoverDetailActivity", cId);
                Picasso.with(MyApp.getContext()).load(bean.getData().getDesigner().getAvatar_url()).into(userIv);
                adapter.setData(bean.getData().getImages());
                listView.setAdapter(adapter);
                tAdapter.setData(bean);
                mListView.setAdapter(tAdapter);
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent1 = new Intent(DiscoverDetailActivity.this, MagazineActivity.class);
                        String mId = String.valueOf(bean.getData().getRefer_articles().get(position).getId());
                        intent1.putExtra("id",mId);
                        startActivity(intent1);
                    }
                });


            }
        }, new NetListener.OnError() {
            @Override
            public void onError() {

            }
        });


        commentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DiscoverDetailActivity.this,DiscoverCommentActivity.class);
                intent1.putExtra("content",editText.getText().toString());
                intent1.putExtra("cId",cId);
                startActivity(intent1);
            }
        });
        BmobQuery<CommentBean> query = new BmobQuery<>();
        query.addWhereEqualTo("id",cId);
        query.findObjects(DiscoverDetailActivity.this, new FindListener<CommentBean>() {
            @Override
            public void onSuccess(List<CommentBean> list) {
                countTv.setText(String.valueOf(list.size()));
            }

            @Override
            public void onError(int i, String s) {
                countTv.setText("0");
            }
        });
    }
}
