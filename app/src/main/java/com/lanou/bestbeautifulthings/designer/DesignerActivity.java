package com.lanou.bestbeautifulthings.designer;

import android.util.Log;
import android.widget.GridView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.base.BaseActivity;
import com.lanou.bestbeautifulthings.base.MyApp;
import com.lanou.bestbeautifulthings.net.NetListener;
import com.lanou.bestbeautifulthings.net.NetRequest;

/**
 * Created by dllo on 16/7/25.
 */
public class DesignerActivity extends BaseActivity {
    private GridView designerGridView;
    private DesignerAdapter designerAdapter;
    private DesignerBean designerBean;

    @Override
    public int setLayout() {
        return R.layout.activity_designer;
    }

    @Override
    protected void initView() {
        designerGridView = (MyGridView) findViewById(R.id.designer_gridView);
    }

    @Override
    protected void initData() {
        designerAdapter = new DesignerAdapter(MyApp.getContext());
        NetRequest.getInstance().getDesignerBean(DesignerBean.class, new NetListener.OnSucceed<DesignerBean>() {
            @Override
            public void OnSucceed(DesignerBean result) {

                Log.d("DesignerActivity", "result:" + result.getData().getDesigners().get(1).getName());
                designerAdapter.setDesignerBean(result);


            }
        }, new NetListener.OnError() {
            @Override
            public void onError() {

            }
        });
        designerGridView.setAdapter(designerAdapter);
    }
}
