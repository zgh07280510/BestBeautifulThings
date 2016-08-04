package com.lanou.bestbeautifulthings.discover.discoverdetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.discover.beans.CommentBean;
import com.lanou.bestbeautifulthings.util.XCRoundImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/8/3.
 */
public class CommentAdapter extends BaseAdapter {
    private List<CommentBean> data;
    private Context context;

    public CommentAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<CommentBean> data) {
        if (this.data == null) {
            this.data = data;

        } else {
            this.data.addAll(data);
        }
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
            convertView = LayoutInflater.from(context).inflate(R.layout.discover_comment_item,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.contentTv.setText(data.get(position).getComment());
        Picasso.with(context).load(data.get(position).getUserIcon()).into(holder.userIv);
        holder.nameTv.setText(data.get(position).getUserName());
        holder.timeTv.setText(data.get(position).getTime());
        return convertView;
    }
    class ViewHolder{
        XCRoundImageView userIv;
        TextView nameTv;
        TextView contentTv;
        TextView timeTv;
        public ViewHolder(View view){
            nameTv = (TextView) view.findViewById(R.id.comment_item_name_tv);
            userIv = (XCRoundImageView) view.findViewById(R.id.comment_item_icon);
            contentTv = (TextView) view.findViewById(R.id.comment_item_content);
            timeTv = (TextView) view.findViewById(R.id.comment_item_time);
        }
    }
}
