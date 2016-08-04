package com.lanou.bestbeautifulthings.magazine.magazinedetail.magazineimage;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lanou.bestbeautifulthings.R;

import java.util.List;

/**
 * Created by zouguohua on 16/8/4.
 */
public class LoadImageAdapter extends PagerAdapter {
    private List<String> urls;
    private Context context;
    private ImageView imageView;

    public LoadImageAdapter(Context context) {
        this.context = context;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_loadimage, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.loadimage_iv);
        Glide.with(context).load(urls.get(position)).into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }
}
