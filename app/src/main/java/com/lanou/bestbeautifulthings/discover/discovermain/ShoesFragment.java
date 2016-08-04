package com.lanou.bestbeautifulthings.discover.discovermain;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.base.BaseFragment;
import com.lanou.bestbeautifulthings.discover.adapter.BagPopuAdapter;
import com.lanou.bestbeautifulthings.discover.adapter.ShoesAdapter;
import com.lanou.bestbeautifulthings.discover.adapter.ShoesPopuAdapter;
import com.lanou.bestbeautifulthings.discover.beans.BagBean;
import com.lanou.bestbeautifulthings.discover.beans.ShoesBean;
import com.lanou.bestbeautifulthings.discover.beans.SpinerBean;
import com.lanou.bestbeautifulthings.net.NetListener;
import com.lanou.bestbeautifulthings.net.NetRequest;
import com.lanou.bestbeautifulthings.util.DisplayUtil;

/**
 * Created by dllo on 16/7/30.
 */
public class ShoesFragment extends BaseFragment {
    private ShoesBean shoesBean;
    private RecyclerView recyclerView;
    private ShoesAdapter adapter;
    private SpinerBean spinerBean;
    private ShoesPopuAdapter popuAdapter;
    private ImageView dropdownIv;
    private PopupWindow popupWindow;
    private GridView gridView;
    private View popuView;
    private RelativeLayout relativeLayout;
    private String mId = "2";
    private TextView popuTv;


    @Override
    protected int setLayout() {
        return R.layout.fragment_shoes;
    }

    @Override
    protected void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_shoes_recyler);
        adapter = new ShoesAdapter(context);
        popuView= LayoutInflater.from(context).inflate(R.layout.bag_fragmnet_popu, null);
        dropdownIv = (ImageView) view.findViewById(R.id.shoes_drop_down);
        popuAdapter = new ShoesPopuAdapter(context);
        gridView = (GridView) popuView.findViewById(R.id.popu_grid);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.shoes_relayout);
        popuTv = (TextView) view.findViewById(R.id.shoes_popu_tv);
    }

    @Override
    protected void initData() {


        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropdownIv.setImageResource(R.mipmap.icon_whiteback_up);
                popuTv.setVisibility(View.GONE);
                popuWindows();
            }
        });

        NetRequest.getInstance().getDiscoverDtailInformation(6,"",SpinerBean.class, new NetListener.OnSucceed<SpinerBean>() {
            @Override
            public void OnSucceed(SpinerBean result) {
                spinerBean = result;
                spinerBean.getData().getCategories().get(1).getSub_categories().get(0).setName("全部");
                spinerBean.getData().getCategories().get(1).getSub_categories().get(0).setId(2);
                popuAdapter.setBean(spinerBean);
                gridView.setAdapter(popuAdapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mId = String.valueOf(spinerBean.getData().getCategories().get(1).getSub_categories().get(position).getId());
                        updateBean();
                        popupWindow.dismiss();
                        popuTv.setVisibility(View.VISIBLE);
                        popuTv.setText(spinerBean.getData().getCategories().get(1).getSub_categories().get(position).getName());
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
        NetRequest.getInstance().getDiscoverDtailInformation(2,mId,ShoesBean.class, new NetListener.OnSucceed<ShoesBean>() {
            @Override
            public void OnSucceed(ShoesBean result) {
                shoesBean = result;
                adapter.setShoesBean(shoesBean);
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
        WindowManager wm = (WindowManager)context
                .getSystemService(Context.WINDOW_SERVICE);

        int width = (int) (wm.getDefaultDisplay().getWidth());

        popupWindow = new PopupWindow(popuView, width, DisplayUtil.px2dip(context,2200), false) {
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
