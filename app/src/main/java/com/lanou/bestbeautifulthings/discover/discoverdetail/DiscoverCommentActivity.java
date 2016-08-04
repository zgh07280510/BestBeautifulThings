package com.lanou.bestbeautifulthings.discover.discoverdetail;


import android.content.Intent;
import android.view.View;

import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.base.BaseActivity;
import com.lanou.bestbeautifulthings.discover.beans.CommentBean;
import com.lanou.bestbeautifulthings.util.LoadPopu;
import com.lanou.bestbeautifulthings.util.XCRoundImageView;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by dllo on 16/8/3.
 */
public class DiscoverCommentActivity extends BaseActivity {
    private EditText editText;
    private ListView listView;
    private CommentAdapter adapter;
    private List<CommentBean> data;
    private Platform qq;
    private Platform sina;
    private TextView sendTv;
    private String id;
    private XCRoundImageView userIv;





    @Override
    public int setLayout() {
        return R.layout.activity_discover_comment;
    }

    @Override
    protected void initView() {
        sendTv = (TextView) findViewById(R.id.discover_comment_send);
        editText = (EditText) findViewById(R.id.discover_edit);
        listView = (ListView) findViewById(R.id.discover_comment_list);
        userIv = (XCRoundImageView) findViewById(R.id.discover_bottom_icon);
        adapter = new CommentAdapter(this);
        data = new ArrayList<>();
        ShareSDK.initSDK(this);
        sina = ShareSDK.getPlatform(SinaWeibo.NAME);
        qq = ShareSDK.getPlatform(QQ.NAME);



    }

    @Override
    protected void initData() {
        if (qq.isValid()){
            Picasso.with(this).load(qq.getDb().getUserIcon()).into(userIv);
        }else if (sina.isValid()){
            Picasso.with(this).load(sina.getDb().getUserIcon()).into(userIv);
        }
        Intent intent = getIntent();
        final String content = intent.getStringExtra("content");
        id = intent.getStringExtra("cId");
        editText.setText(content);
        ShareSDK.initSDK(this);
        sina = ShareSDK.getPlatform(SinaWeibo.NAME);
        qq = ShareSDK.getPlatform(QQ.NAME);
        BmobQuery<CommentBean> query = new BmobQuery<>();
        query.addWhereEqualTo("id",id);
        query.findObjects(DiscoverCommentActivity.this, new FindListener<CommentBean>() {
            @Override
            public void onSuccess(List<CommentBean> list) {
                adapter.setData(list);
                listView.setAdapter(adapter);
            }

            @Override
            public void onError(int i, String s) {

            }
        });

        sendTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qq.isValid()){
                    CommentBean bean = new CommentBean();
                    bean.setUserName(qq.getDb().getUserName());
                    bean.setComment(editText.getText().toString());
                    bean.setUserIcon(qq.getDb().getUserIcon());
                    bean.setId(id);
                    bean.setTime(dateToString(gainCurrentDate(),"yyyy年MM月dd日 HH:mm:ss"));
                    bean.save(DiscoverCommentActivity.this, new SaveListener() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onFailure(int i, String s) {

                        }
                    });
                    if (data.size()>0){
                    data.remove(0);
                    }
                    data.add(bean);
                    adapter.setData(data);
                    listView.setAdapter(adapter);
                    editText.setText("");
                }else if (sina.isValid()){
                    CommentBean bean = new CommentBean();
                    bean.setUserName(sina.getDb().getUserName());
                    bean.setComment(editText.getText().toString());
                    bean.setUserIcon(sina.getDb().getUserIcon());
                    bean.setId(id);
                    bean.setTime(dateToString(gainCurrentDate(),"yyyy年MM月dd日HH:mm:ss"));
                    bean.save(DiscoverCommentActivity.this, new SaveListener() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onFailure(int i, String s) {

                        }
                    });
                    if (data.size()>0){
                        data.remove(0);
                    }
                    data.add(bean);
                    adapter.setData(data);
                    listView.setAdapter(adapter);
                    editText.setText("");

                }else{

                    LoadPopu.showLoadPopu(DiscoverCommentActivity.this);

                }


            }
        });


    }
    /**
     * 获取系统当前时间
     *
     * @return
     */
    public static Date gainCurrentDate() {
        return new Date();
    }
    /**
     * 将data类型转换为String类型
     *
     * @param data
     * @param formatType
     * @return
     */
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }
}
