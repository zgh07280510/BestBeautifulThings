package com.lanou.bestbeautifulthings.discover.discovermain;

import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.base.BaseFragment;
import com.lanou.bestbeautifulthings.discover.adapter.BagFragmentAdapter;
import com.lanou.bestbeautifulthings.discover.adapter.BagPopuAdapter;
import com.lanou.bestbeautifulthings.discover.beans.BagBean;
import com.lanou.bestbeautifulthings.discover.beans.SpinerBean;
import com.lanou.bestbeautifulthings.net.NetListener;
import com.lanou.bestbeautifulthings.net.NetRequest;
import com.lanou.bestbeautifulthings.util.DisplayUtil;

/**
 * Created by dllo on 16/7/30.
 */
public class BagFragment extends BaseFragment {
    private BagBean bagBean;
    private BagFragmentAdapter adapter;
    private RecyclerView recyclerView;
    private SpinerBean spinerBean;
    private BagPopuAdapter popuAdapter;
    private ImageView dropdownIv;
    private PopupWindow popupWindow;
    private GridView gridView;
    private View popuView;
    private RelativeLayout relativeLayout;
    private String mId = "1";
    private TextView popuTv;
    @Override
    protected int setLayout() {
        return R.layout.fragment_bag;
    }

    @Override
    protected void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_bag_recyler);
        adapter = new BagFragmentAdapter(context);
        popuView= LayoutInflater.from(context).inflate(R.layout.bag_fragmnet_popu, null);
        dropdownIv = (ImageView) view.findViewById(R.id.bag_drop_down);
        popuAdapter = new BagPopuAdapter(context);
        gridView = (GridView) popuView.findViewById(R.id.popu_grid);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.bag_relayout);
        popuTv = (TextView) view.findViewById(R.id.bag_popu_tv);

    }

    @Override
    protected void initData() {


        dropdownIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropdownIv.setImageResource(R.mipmap.icon_whiteback_up);
                popuWindows();
                popuTv.setVisibility(View.GONE);
            }
        });

        NetRequest.getInstance().getDiscoverDtailInformation(6,"",SpinerBean.class, new NetListener.OnSucceed<SpinerBean>() {
            @Override
            public void OnSucceed(SpinerBean result) {
                spinerBean = result;
                spinerBean.getData().getCategories().get(0).getSub_categories().get(0).setName("全部");
                spinerBean.getData().getCategories().get(0).getSub_categories().get(0).setId(1);
                popuAdapter.setBean(spinerBean);
                gridView.setAdapter(popuAdapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mId = String.valueOf(spinerBean.getData().getCategories().get(0).getSub_categories().get(position).getId());
                        updateBean();
                        popupWindow.dismiss();
                        popuTv.setVisibility(View.VISIBLE);
                        popuTv.setText(spinerBean.getData().getCategories().get(0).getSub_categories().get(position).getName());

                    }
                });

            }
        }, new NetListener.OnError() {
            @Override
            public void onError() {

            }
        });
        updateBean();



    }
    public void updateBean(){
        NetRequest.getInstance().getDiscoverDtailInformation(1,mId,BagBean.class, new NetListener.OnSucceed<BagBean>() {
            @Override
            public void OnSucceed(BagBean result) {
                bagBean = result;
                adapter.setBagBean(bagBean);
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
    public void popuWindows(){


        popupWindow = new PopupWindow(popuView, DisplayUtil.px2dip(context,5200), DisplayUtil.px2dip(context,2200), false) {
            @Override
            public void dismiss() {
                super.dismiss();
                dropdownIv.setImageResource(R.mipmap.icon_whiteback_normal);
                popuTv.setVisibility(View.VISIBLE);

            }
        };
        //外部获得焦点
        popupWindow.setOutsideTouchable(true);
        //内部获得焦点
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setInputMethodMode(popupWindow.INPUT_METHOD_NEEDED);
        popupWindow.showAsDropDown(relativeLayout);

    }
}
