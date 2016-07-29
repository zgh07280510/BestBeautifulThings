package com.lanou.bestbeautifulthings.designer.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.base.BaseFragment;
import com.lanou.bestbeautifulthings.designer.adapter.IntroduceAdapter;
import com.lanou.bestbeautifulthings.designer.bean.DesignerWorksBean;
import com.lanou.bestbeautifulthings.net.NetListener;
import com.lanou.bestbeautifulthings.net.NetRequest;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/28.
 */
public class IntroduceFragment extends BaseFragment {
    private RecyclerView rvIntroduce;
    private IntroduceAdapter introduceAdapter;


    protected int setLayout() {
        return R.layout.fragment_introduce;
    }

    @Override
    protected void initView(View view) {
        rvIntroduce = (RecyclerView) view.findViewById(R.id.introduce_recyclerView);

    }

    @Override
    protected void initData() {
        introduceAdapter = new IntroduceAdapter(context);


        Bundle bundle = getArguments();
        String id = bundle.getString("id");
        NetRequest.getInstance().getDesignerWorksBean(id, DesignerWorksBean.class, new NetListener.OnSucceed<DesignerWorksBean>() {
            @Override
            public void OnSucceed(DesignerWorksBean result) {


                introduceAdapter.setData(result);
                rvIntroduce.setAdapter(introduceAdapter);
                rvIntroduce.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

            }
        }, new NetListener.OnError() {
            @Override
            public void onError() {

            }
        });

        introduceAdapter.setOnClickListener(new IntroduceAdapter.OnClickListener() {
            @Override
            public void OnClick(int position) {

            }
        });
    }
}
