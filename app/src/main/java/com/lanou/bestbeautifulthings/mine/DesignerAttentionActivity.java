package com.lanou.bestbeautifulthings.mine;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.base.BaseActivity;
import com.lanou.bestbeautifulthings.base.MyApp;
import com.lanou.bestbeautifulthings.designer.SingleLiteOrm;
import com.lanou.bestbeautifulthings.designer.activity.DesignerInformationActivity;
import com.lanou.bestbeautifulthings.designer.bean.DesignerAttentionBean;

import java.util.ArrayList;

/**
 * Created by dllo on 16/8/2.
 */
public class DesignerAttentionActivity extends BaseActivity {
    private GridView gridView;
    private DesignerAttentionAdapter designerAttentionAdapter;
    private ArrayList<DesignerAttentionBean> designerAttentionBean;


    @Override
    public int setLayout() {
        return R.layout.activity_designer_attention;
    }

    @Override
    protected void initView() {
        gridView = (GridView) findViewById(R.id.designer_attention_gridView);
    }

    @Override
    protected void initData() {
        designerAttentionAdapter = new DesignerAttentionAdapter(MyApp.getContext());
        designerAttentionBean = SingleLiteOrm.getSingleLiteOrm().getLiteOrm().query(DesignerAttentionBean.class);
        designerAttentionAdapter.setDesignerAttentionBean(designerAttentionBean);
        gridView.setAdapter(designerAttentionAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(DesignerAttentionActivity.this,DesignerInformationActivity.class);
                intent.putExtra("id",designerAttentionBean.get(position).getId());
                startActivity(intent);

            }
        });
    }
}
