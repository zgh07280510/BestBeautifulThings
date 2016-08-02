package com.lanou.bestbeautifulthings.designer.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.base.BaseFragment;
import com.lanou.bestbeautifulthings.designer.bean.PictorialBean;
import com.lanou.bestbeautifulthings.net.NetListener;
import com.lanou.bestbeautifulthings.net.NetRequest;

/**
 * Created by dllo on 16/7/28.
 */
public class PictorialFragment extends BaseFragment {
    private TextView tvTitle, tvSubTitle;
    private ImageView ivPic;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_pictorial;
    }

    @Override
    protected void initView(View view) {
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvSubTitle = (TextView) view.findViewById(R.id.tv_sub_title);
        ivPic = (ImageView) view.findViewById(R.id.iv_pic);

    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        String id = bundle.getString("id");
        NetRequest.getInstance().getPictorialBean(id, PictorialBean.class, new NetListener.OnSucceed<PictorialBean>() {
            @Override
            public void OnSucceed(PictorialBean result) {
            tvTitle.setText(result.getData().getArticles().get(0).getTitle());
                tvSubTitle.setText(result.getData().getArticles().get(0).getSub_title());
                Glide.with(context).load(result.getData().getArticles().get(0).getImage_url()).into(ivPic);
            }
        }, new NetListener.OnError() {
            @Override
            public void onError() {

            }
        });
    }


}
