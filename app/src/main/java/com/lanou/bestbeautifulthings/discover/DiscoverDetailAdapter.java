package com.lanou.bestbeautifulthings.discover;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lanou.bestbeautifulthings.R;


/**
 * Created by dllo on 16/7/29.
 */
public class DiscoverDetailAdapter extends BaseAdapter {
    private DiscoverBean datas;
    private Context context;

    public DiscoverDetailAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(DiscoverBean datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.getData().getActivities().size();
    }

    @Override
    public Object getItem(int position) {
        return datas.getData().getActivities().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DetailViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.discover_detail_item,parent,false);
            holder = new DetailViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (DetailViewHolder) convertView.getTag();
        }
        holder.userlabelTv.setText(datas.getData().getActivities().get(position).getDesigner().getLabel());
        holder.userNameTv.setText(datas.getData().getActivities().get(position).getDesigner().getName());
        Glide.with(context).load(datas.getData().getActivities().get(position).getDesigner().getAvatar_url()).into(holder.userIv);
        Glide.with(context).load(datas.getData().getActivities().get(position).getImages()).into(holder.coverIv);
        holder.digestTv.setText(datas.getData().getActivities().get(position).getDigest());
        return convertView;
    }
    class DetailViewHolder{
        ImageView coverIv;
        TextView digestTv;
        ImageView userIv;
        TextView userNameTv;
        TextView userlabelTv;
        public DetailViewHolder(View view){
            coverIv = (ImageView) view.findViewById(R.id.discover_cover_iv);
            digestTv = (TextView) view.findViewById(R.id.discover_digest);
            userIv = (ImageView) view.findViewById(R.id.user_image);
            userNameTv = (TextView) view.findViewById(R.id.user_name);
            userlabelTv = (TextView) view.findViewById(R.id.user_label);
        }

    }
}
