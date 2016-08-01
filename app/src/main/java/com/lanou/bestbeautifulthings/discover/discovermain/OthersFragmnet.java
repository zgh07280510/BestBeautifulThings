package com.lanou.bestbeautifulthings.discover.discovermain;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.base.BaseFragment;
import com.lanou.bestbeautifulthings.discover.adapter.OthersAdapter;
import com.lanou.bestbeautifulthings.discover.beans.OthersBean;
import com.lanou.bestbeautifulthings.net.NetListener;
import com.lanou.bestbeautifulthings.net.NetRequest;

/**
 * Created by dllo on 16/7/30.
 */
public class OthersFragmnet extends BaseFragment {
    private OthersBean bean;
    private RecyclerView recyclerView;
    private OthersAdapter adapter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_others;
    }

    @Override
    protected void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_others_recyler);
        adapter = new OthersAdapter(context);
    }

    @Override
    protected void initData() {
        NetRequest.getInstance().getDiscoverDtailInformation(5,"",OthersBean.class, new NetListener.OnSucceed<OthersBean>() {
            @Override
            public void OnSucceed(OthersBean result) {
                bean = result;
                adapter.setBean(bean);
                recyclerView.setAdapter(adapter);

            }
        }, new NetListener.OnError() {
            @Override
            public void onError() {

            }
        });
        GridLayoutManager manager = new GridLayoutManager(context,2);
        recyclerView.setLayoutManager(manager);
    }
}
