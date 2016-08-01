package com.lanou.bestbeautifulthings.discover.discoverdetail;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.lanou.bestbeautifulthings.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/7/31.
 */
public class DetailActivityAdapter extends BaseAdapter {
    private List<String> data;
    private Context context;

    public DetailActivityAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();

    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.detail_item,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(data.get(position)).resize(480,320).into(holder.imageView);
        return convertView;
    }

    class ViewHolder{
        ImageView imageView;

        public ViewHolder(View view){
            imageView = (ImageView) view.findViewById(R.id.detai_item_iv);
        }

    }
}
