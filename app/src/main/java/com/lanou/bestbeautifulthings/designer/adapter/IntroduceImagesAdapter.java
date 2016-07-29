package com.lanou.bestbeautifulthings.designer.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.designer.bean.DesignerInformationBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/7/28.
 */
public class IntroduceImagesAdapter extends PagerAdapter {
    private Context context;
    private ImageView imageView;
    private List<DesignerInformationBean.DataBean> designerInformationBean ;
    private List<View> viewList;

    public IntroduceImagesAdapter(Context context) {
        this.context = context;
    }

    public void setDesignerInformationBean(List<DesignerInformationBean.DataBean> designerInformationBean) {
        this.designerInformationBean = designerInformationBean;
        viewList = new ArrayList<>();
        for (DesignerInformationBean.DataBean bean:designerInformationBean){
            viewList.add(null);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return designerInformationBean== null?0:designerInformationBean.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
      DesignerInformationBean.DataBean bean = designerInformationBean.get(position);
      View view= LayoutInflater.from(context).inflate(R.layout.introduce_image,null);
      imageView = (ImageView) view.findViewById(R.id.iv_introduce);
        Glide.with(context).load(designerInformationBean.get(position).getIntroduce_images()).into(imageView);
        container.addView(view);
         viewList.set(position,view);

        return view;
    }


}
