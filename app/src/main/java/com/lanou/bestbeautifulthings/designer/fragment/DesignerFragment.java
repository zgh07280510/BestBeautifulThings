package com.lanou.bestbeautifulthings.designer.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.base.BaseFragment;
import com.lanou.bestbeautifulthings.base.MyApp;
import com.lanou.bestbeautifulthings.designer.SingleLiteOrm;
import com.lanou.bestbeautifulthings.designer.activity.DesignerInformationActivity;
import com.lanou.bestbeautifulthings.designer.bean.DesignerAttentionBean;
import com.lanou.bestbeautifulthings.designer.eventbus.TvStateEventBus;
import com.lanou.bestbeautifulthings.designer.interfaces.AttentionClick;
import com.lanou.bestbeautifulthings.designer.view.GridViewPullToRefreshView;
import com.lanou.bestbeautifulthings.designer.adapter.DesignerAdapter;
import com.lanou.bestbeautifulthings.designer.bean.DesignerBean;
import com.lanou.bestbeautifulthings.net.NetListener;
import com.lanou.bestbeautifulthings.net.NetRequest;
import com.litesuits.orm.db.assit.QueryBuilder;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by zouguohua on 16/7/26.
 */
public class DesignerFragment extends BaseFragment implements AttentionClick {
    private GridView designerGridView;
    private DesignerAdapter designerAdapter;
    private GridViewPullToRefreshView gridViewPullToRefreshView;
    private DesignerBean data;
    private int id;


    @Override
    protected int setLayout() {
        return R.layout.fragment_designer;
    }

    @Override
    protected void initView(View view) {
        designerGridView = (GridView) view.findViewById(R.id.designer_gridView);
        gridViewPullToRefreshView = (GridViewPullToRefreshView) view.findViewById(R.id.designer_pull_refresh_view);


    }

    @Override
    protected void initData() {
        designerAdapter = new DesignerAdapter(MyApp.getContext());
        designerAdapter.setAttentionClick(this);
        NetRequest.getInstance().getDesignerBean(DesignerBean.class, new NetListener.OnSucceed<DesignerBean>() {
            @Override
            public void OnSucceed(DesignerBean result) {
                data = result;
                designerAdapter.setDesignerBean(result);

            }
        }, new NetListener.OnError() {
            @Override
            public void onError() {
            }
        });

        designerGridView.setAdapter(designerAdapter);
        designerGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(), DesignerInformationActivity.class);
                intent.putExtra("id", data.getData().getDesigners().get(position).getId());
                startActivity(intent);

            }
        });

        //给下拉刷新加监听
        gridViewPullToRefreshView.setEnablePullLoadMoreDataStatus(false);
        gridViewPullToRefreshView.setEnablePullToRefresh(false);
        gridViewPullToRefreshView.setOnHeaderRefreshListener(new GridViewPullToRefreshView.OnHeaderRefreshListener() {
            @Override
            public void onHeaderRefresh(GridViewPullToRefreshView view) {
                gridViewPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //设置更新时间
//                        gridViewPullToRefreshView.onHeaderRefreshComplete("最近更新:01-23 12:33");
                        designerAdapter.notifyDataSetChanged();
                        Toast.makeText(context, "刷新成功", Toast.LENGTH_SHORT).show();
                        gridViewPullToRefreshView.onHeaderRefreshComplete();
                    }
                }, 1000);

            }

        });


    }

    @Override
    public void onClick(int position) {

        QueryBuilder<DesignerAttentionBean> queryBuilder = new QueryBuilder<DesignerAttentionBean>(DesignerAttentionBean.class);
        id = data.getData().getDesigners().get(position).getId();
        queryBuilder.whereEquals("id", id);
            DesignerAttentionBean bean = new DesignerAttentionBean();
        if (SingleLiteOrm.getSingleLiteOrm().getLiteOrm().query(queryBuilder).size() == 0) {
            bean.setId(id);
            bean.setAvatar(data.getData().getDesigners().get(position).getAvatar_url());
            bean.setLabel(data.getData().getDesigners().get(position).getLabel());
            bean.setName(data.getData().getDesigners().get(position).getName());
            bean.setRecommendImage(data.getData().getDesigners().get(position).getRecommend_images().get(0));
            SingleLiteOrm.getSingleLiteOrm().getLiteOrm().insert(bean);
        }else {
           SingleLiteOrm.getSingleLiteOrm().getLiteOrm().delete(bean);
            EventBus.getDefault().post(new TvStateEventBus(0));
        }
    }
}