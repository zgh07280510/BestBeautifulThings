package com.lanou.bestbeautifulthings.discover.discovermain;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.base.BaseFragment;
import com.lanou.bestbeautifulthings.base.MyApp;
import com.lanou.bestbeautifulthings.designer.activity.DesignerInformationActivity;
import com.lanou.bestbeautifulthings.discover.discoverdetail.DiscoverDetailActivity;
import com.lanou.bestbeautifulthings.net.NetListener;
import com.lanou.bestbeautifulthings.net.NetRequest;
import com.lanou.bestbeautifulthings.util.OnRefreshListener;
import com.lanou.bestbeautifulthings.util.RefreshListView;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by zouguohua on 16/7/26.
 */
public class YouWuFragment extends BaseFragment implements OnRefreshListener,UserListener {
    private DiscoverYouWuAdapter adapter;
    private RefreshListView listView;
    private Date date;
    private DiscoverBean bean;
    private Date mDate;
    private String mId;


    @Override
    protected int setLayout() {

        return R.layout.fragment_discover;

    }

    @Override
    protected void initView(View view) {
        listView = (RefreshListView) view.findViewById(R.id.discover_list);

    }

    @Override
    protected void initData() {
        adapter = new DiscoverYouWuAdapter(MyApp.getContext());
        adapter.setListener(this);
        date = gainCurrentDate();
        listView.setOnRefreshListener(this);
        mDate = subDateTime(date,24);
        NetRequest.getInstance().getDiscoverDtailInformationBean(String.valueOf(dateToLong(date)),DiscoverBean.class, new NetListener.OnSucceed<DiscoverBean>() {
            @Override
            public void OnSucceed(DiscoverBean result) {
                bean = result;
                adapter.setDatas(bean);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(context, DiscoverDetailActivity.class);
                        mId = String.valueOf(bean.getData().getActivities().get(position-1).getProduct().getId());
                        intent.putExtra("id",mId);

                        context.startActivity(intent);
                    }
                });
            }
        }, new NetListener.OnError() {
            @Override
            public void onError() {

            }
        });

    }

    /**
     * 对日期进行相减操作
     *
     * @param target 需要进行运算的日期
     * @param hour   小时
     * @return
     */
    public static Date subDateTime(Date target, double hour) {
        if (null == target || hour < 0) {
            return target;
        }

        return new Date(target.getTime() - (long) (hour * 60 * 60 * 1000));
    }


    /**
     * 获取系统当前时间
     *
     * @return
     */
    public static Date gainCurrentDate() {
        return new Date();
    }

    /**
     * 将data类型转换为long类型
     *
     * @param date
     * @return
     */
    public static long dateToLong(Date date) {
        return date.getTime();
    }



    @Override
    public void onDownPullRefresh() {
        initData();
        listView.hideHeaderView();
    }

    @Override
    public void onLoadingMore() {

        loading();
        mDate = subDateTime(mDate,24);
        listView.hideFooterView();

    }

    public void loading(){
        NetRequest.getInstance().getDiscoverDtailInformationBean(String.valueOf(dateToLong(mDate)),DiscoverBean.class, new NetListener.OnSucceed<DiscoverBean>() {
            @Override
            public void OnSucceed(DiscoverBean result) {
                bean = result;
                adapter.setDatas(bean);
                listView.setAdapter(adapter);

            }
        }, new NetListener.OnError() {
            @Override
            public void onError() {

            }
        });

    }


    @Override
    public void userClick(int position) {
        Intent intent = new Intent(context, DesignerInformationActivity.class);
        intent.putExtra("id",bean.getData().getActivities().get(position).getDesigner().getId());
        context.startActivity(intent);
    }
}
