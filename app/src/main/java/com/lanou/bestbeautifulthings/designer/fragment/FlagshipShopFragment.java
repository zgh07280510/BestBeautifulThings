package com.lanou.bestbeautifulthings.designer.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.base.BaseFragment;
import com.lanou.bestbeautifulthings.designer.bean.FlagshipAndBuyOnLineBean;
import com.lanou.bestbeautifulthings.net.NetListener;
import com.lanou.bestbeautifulthings.net.NetRequest;

/**
 * Created by dllo on 16/7/28.
 */
public class FlagshipShopFragment extends BaseFragment {
    private ImageView ivFlagship;
    private TextView tvCity, tvName, tvAddress;

    @Override
    protected int setLayout() {
        return R.layout.fragment_flagship_shop;
    }

    @Override
    protected void initView(View view) {
        ivFlagship = (ImageView) view.findViewById(R.id.iv_flag);
        tvCity = (TextView) view.findViewById(R.id.tv_flagship_city);
        tvName = (TextView) view.findViewById(R.id.tv_flagship_name);
        tvAddress = (TextView) view.findViewById(R.id.tv_flagship_address);
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        String id = bundle.getString("id");
        NetRequest.getInstance().getFlagshipAndBuyOnlineBean(id, FlagshipAndBuyOnLineBean.class, new NetListener.OnSucceed<FlagshipAndBuyOnLineBean>() {
            @Override
            public void OnSucceed(FlagshipAndBuyOnLineBean result) {
                 if (result.getData().getShops().size()>0){

                Glide.with(context).load(result.getData().getShop_image()).into(ivFlagship);
                tvCity.setText(result.getData().getShops().get(0).getCity());
                tvName.setText(result.getData().getShops().get(0).getName());
                tvAddress.setText(result.getData().getShops().get(0).getAddress());
                 }

            }
        }, new NetListener.OnError() {
            @Override
            public void onError() {

            }
        });
    }
}
