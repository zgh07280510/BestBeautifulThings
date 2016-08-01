package com.lanou.bestbeautifulthings.discover.discoverdetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou.bestbeautifulthings.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/8/1.
 */
public class DetailactivityTitleAdapter extends BaseAdapter {
    private DetailBean data;
    private Context context;

    public DetailactivityTitleAdapter(Context context) {
        this.context = context;
    }

    public void setData(DetailBean data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.getData().getRefer_articles().size();

    }

    @Override
    public Object getItem(int position) {
        return data.getData().getRefer_articles().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.detail_title_item,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.subTv.setText(data.getData().getRefer_articles().get(position).getSub_title());
        holder.titleTv.setText(data.getData().getRefer_articles().get(position).getTitle());
        Picasso.with(context).load(data.getData().getRefer_articles().get(position).getImage_url()).resize(480,320).into(holder.imageView);
        return convertView;
    }

    class ViewHolder{
        ImageView imageView;
        TextView titleTv,subTv;
        public ViewHolder(View view){
            titleTv = (TextView) view.findViewById(R.id.detail_title_tv);
            subTv = (TextView) view.findViewById(R.id.detail_sub_title);
            imageView = (ImageView) view.findViewById(R.id.detail_title_image);
        }

    }
}
