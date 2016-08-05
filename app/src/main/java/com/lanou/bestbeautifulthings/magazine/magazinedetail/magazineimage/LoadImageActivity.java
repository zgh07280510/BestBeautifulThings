package com.lanou.bestbeautifulthings.magazine.magazinedetail.magazineimage;

import android.support.v4.view.ViewPager;
import android.util.Log;

import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.base.BaseActivity;
import com.lanou.bestbeautifulthings.magazine.magazinedetail.magazineimage.SetImage;

import java.util.List;

/**
 * Created by zouguohua on 16/8/3.
 */
public class LoadImageActivity extends BaseActivity {
    private List<String> urls;
    private ViewPager loadImageVp;
    private LoadImageAdapter loadImageAdapter;

    @Override
    public int setLayout() {

        return R.layout.activity_loadimage;
    }

    @Override
    protected void initView() {
        loadImageVp = (ViewPager) findViewById(R.id.loadimage_vp);
    }

    @Override
    protected void initData() {
        urls = getIntent().getStringArrayListExtra("urls");
        loadImageAdapter = new LoadImageAdapter(this);
        loadImageAdapter.setUrls(urls);
        loadImageVp.setAdapter(loadImageAdapter);

    }
}
