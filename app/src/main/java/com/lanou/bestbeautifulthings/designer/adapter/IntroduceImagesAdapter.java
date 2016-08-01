package com.lanou.bestbeautifulthings.designer.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lanou.bestbeautifulthings.base.MyApp;
import com.lanou.bestbeautifulthings.designer.bean.DesignerInformationBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/7/28.
 */
public class IntroduceImagesAdapter extends PagerAdapter {
    private List<ImageView> imgs;

    private Context context;

    public IntroduceImagesAdapter(Context context) {
        this.context = context;
    }

    public void setDesignerInformationBean(DesignerInformationBean designerInformationBean) {

        imgs = new ArrayList<>();
        for (int i = 0; i < designerInformationBean.getData().getIntroduce_images().size(); i++) {
            ImageView imageView = new ImageView(context);
            Glide.with(MyApp.getContext()).load(designerInformationBean.getData().getIntroduce_images().get(i))
                    .centerCrop().crossFade().into(imageView);
            imgs.add(imageView);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return imgs.size();

    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        //取出指定位置的图片ImageView
        ImageView imageView = imgs.get(position % imgs.size());
        /**
         当图片少的时候,不会触发destroyItem
         这个时候去向container中addView会报错
         手动捕获这个异常
         */
        try {
            container.addView(imageView);
        } catch (IllegalStateException e) {
            container.removeView(imageView);
            container.addView(imageView);
        }

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (container.getChildAt(position % imgs.size()) == object) {
            //销毁指定位置的ImageView回收内存
            container.removeViewAt(position % imgs.size());
        }
    }


}
