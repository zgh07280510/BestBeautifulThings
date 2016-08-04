package com.lanou.bestbeautifulthings.mine;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.base.BaseFragment;
import com.lanou.bestbeautifulthings.util.LoadPopu;
import com.lanou.bestbeautifulthings.util.XCRoundImageView;
import com.squareup.picasso.Picasso;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by zouguohua on 16/7/26.
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {
    private XCRoundImageView iconIv;
    private UserBrodCastReceiver receiver;
    private TextView userName;
    private Platform qq,weibo;
    private TextView setting;
    private UserSinaReceiver sinaReceiver;
    private RelativeLayout rlDesignerAttention;


    @Override
    protected int setLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {
        iconIv = (XCRoundImageView) view.findViewById(R.id.mine_icon);
        userName = (TextView) view.findViewById(R.id.mine_user_name);
        setting = (TextView) view.findViewById(R.id.mine_setting);
        rlDesignerAttention = (RelativeLayout) view.findViewById(R.id.rl_designer_attention);


    }

    @Override
    protected void initData() {
        ShareSDK.initSDK(context);
        qq = ShareSDK.getPlatform(QQ.NAME);
        weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
        if (qq.isValid()) {
            Picasso.with(context).load(LoadPopu.getQQUserInfo().getUserQQImageUrl()).into(iconIv);
            userName.setText(qq.getDb().getUserName());
        }
        if (weibo.isValid()){
            Picasso.with(context).load(LoadPopu.getSinaUserInfo().getUserSinaImagUrl()).into(iconIv);
            userName.setText(weibo.getDb().getUserName());
        }
        iconIv.setOnClickListener(this);
        setting.setOnClickListener(this);
        rlDesignerAttention.setOnClickListener(this);
        receiver = new UserBrodCastReceiver();
        sinaReceiver = new UserSinaReceiver();
        IntentFilter filter = new IntentFilter("load is sucess");
        context.registerReceiver(receiver, filter);

        IntentFilter filter1 = new IntentFilter("sina load is success");
        context.registerReceiver(sinaReceiver,filter1);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_icon:
                if (qq.isValid()||weibo.isValid()) {
                    return;
                } else {

                    LoadPopu.showLoadPopu(context);
                }

                break;
            case R.id.mine_setting:
                if (qq.isValid()) {
                    qq.removeAccount();
                    iconIv.setImageResource(R.mipmap.fffffff);
                    userName.setText("请登录");
                }
                if (weibo.isValid()){
                    weibo.removeAccount();
                    iconIv.setImageResource(R.mipmap.fffffff);
                    userName.setText("请登录");
                }

                break;
            case R.id.rl_designer_attention:
                Intent intent = new Intent(getActivity(),DesignerAttentionActivity.class);
                startActivity(intent);
                break;
        }

    }

    public class UserBrodCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Picasso.with(context).load(LoadPopu.getQQUserInfo().getUserQQImageUrl()).into(iconIv);
            userName.setText(LoadPopu.getQQUserInfo().getUserQQName());
            Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();


        }

    }
        public class UserSinaReceiver extends BroadcastReceiver{

            @Override
            public void onReceive(Context context, Intent intent) {
                Picasso.with(context).load(LoadPopu.getSinaUserInfo().getUserSinaImagUrl()).into(iconIv);
                userName.setText(LoadPopu.getSinaUserInfo().getUserSinaName());
                Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
            }
        }

    @Override
    public void onDestroy() {
        super.onDestroy();
        context.unregisterReceiver(receiver);
        context.unregisterReceiver(sinaReceiver);
    }
}

