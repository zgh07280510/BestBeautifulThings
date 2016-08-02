package com.lanou.bestbeautifulthings.designer.activity;

import android.content.Intent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.base.BaseActivity;


/**
 * Created by dllo on 16/8/1.
 */
public class BuyOnlineActivity extends BaseActivity implements View.OnClickListener {
    private WebView webView;
    private ImageView ivBack;
    private TextView tvName;
    @Override
    public int setLayout() {
        return R.layout.activity_buy_online;
    }

    @Override
    protected void initView() {
        webView = (WebView) findViewById(R.id.webView);
        ivBack = (ImageView) findViewById(R.id.iv_web_view_back);
        tvName = (TextView) findViewById(R.id.tv_shop_name);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String name =intent.getStringExtra("name");
          webView.loadUrl(url);
        WebSettings webSettings = webView.getSettings();
        webSettings.getAllowFileAccess();
        webSettings.getBuiltInZoomControls();
        webSettings.getJavaScriptEnabled();
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        tvName.setText(name);
        ivBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
