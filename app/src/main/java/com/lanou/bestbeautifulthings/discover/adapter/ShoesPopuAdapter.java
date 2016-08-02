package com.lanou.bestbeautifulthings.discover.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.discover.beans.SpinerBean;

/**
 * Created by dllo on 16/7/31.
 */
public class ShoesPopuAdapter extends BaseAdapter {
    private SpinerBean bean;
    private Context context;


    public ShoesPopuAdapter(Context context) {
        this.context = context;
    }

    public void setBean(SpinerBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return bean.getData().getCategories().get(1).getSub_categories().size();
    }

    @Override
    public Object getItem(int position) {
        return bean.getData().getCategories().get(1).getSub_categories().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_spinner_item,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.spinnerTv.setText(bean.getData().getCategories().get(1).getSub_categories().get(position).getName());
        return convertView;
    }

    class ViewHolder{
        TextView spinnerTv;
        public ViewHolder(View view){
            spinnerTv = (TextView) view.findViewById(R.id.spinner_tv);
        }
    }
}
