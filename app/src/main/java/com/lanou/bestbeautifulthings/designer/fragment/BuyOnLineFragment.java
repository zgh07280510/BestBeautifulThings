package com.lanou.bestbeautifulthings.designer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.base.BaseFragment;
import com.lanou.bestbeautifulthings.designer.activity.BuyOnlineActivity;
import com.lanou.bestbeautifulthings.designer.bean.FlagshipAndBuyOnLineBean;
import com.lanou.bestbeautifulthings.net.NetListener;
import com.lanou.bestbeautifulthings.net.NetRequest;

/**
 * Created by dllo on 16/7/28.
 */
public class BuyOnLineFragment extends BaseFragment {
    private ImageView ivBuyOnline, ivNext;
    private TextView tvBuyOnline;
  private String url,name;
    @Override
    protected int setLayout() {
        return R.layout.fragment_buy_online;
    }

    @Override
    protected void initView(View view) {
        ivNext = (ImageView) view.findViewById(R.id.iv_buy_online_next);
        ivBuyOnline = (ImageView) view.findViewById(R.id.iv_buy_online);
        tvBuyOnline = (TextView) view.findViewById(R.id.tv_buy_online);

    }

    @Override
    protected void initData() {

        Bundle bundle = getArguments();
        String id = bundle.getString("id");
        NetRequest.getInstance().getFlagshipAndBuyOnlineBean(id, FlagshipAndBuyOnLineBean.class, new NetListener.OnSucceed<FlagshipAndBuyOnLineBean>() {
            @Override
            public void OnSucceed(FlagshipAndBuyOnLineBean result) {
               if (result.getData().getOnline_shops().size()>0){

                Glide.with(context).load(result.getData().getOnline_shop_image()).into(ivBuyOnline);
                tvBuyOnline.setText(result.getData().getOnline_shops().get(0).getName());
                url = result.getData().getOnline_shops().get(0).getLink_url();
               name = result.getData().getOnline_shops().get(0).getName();
               }
            }
        }, new NetListener.OnError() {
            @Override
            public void onError() {

            }
        });
        ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BuyOnlineActivity.class);
                intent.putExtra("url",url);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
    }
}
