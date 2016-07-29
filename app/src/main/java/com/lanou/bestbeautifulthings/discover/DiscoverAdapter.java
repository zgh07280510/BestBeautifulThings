package com.lanou.bestbeautifulthings.discover;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lanou.bestbeautifulthings.R;

import com.lanou.bestbeautifulthings.designer.bean.DesignerBean;
import com.lanou.bestbeautifulthings.net.NetListener;
import com.lanou.bestbeautifulthings.net.NetRequest;

import java.util.ArrayList;


/**
 * Created by dllo on 16/7/29.
 */
public class DiscoverAdapter extends BaseAdapter {
    private ArrayList<String> datas;
    private DiscoverBean data;
    private Context context;
    private DiscoverDetailAdapter adapter;


    public DiscoverAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(ArrayList<String> datas) {
        this.datas = datas;
        notifyDataSetChanged();
        adapter = new DiscoverDetailAdapter(context);
        data = new DiscoverBean();

    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.discover_fragment_item,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.dateTv.setText(datas.get(position));
        NetRequest.getInstance().getDiscoverDtailInformationBean(datas.get(position),DiscoverBean.class, new NetListener.OnSucceed<DiscoverBean>() {
            @Override
            public void OnSucceed(DiscoverBean result) {
                Log.d("DiscoverFragment2222212", result.getData().getActivities().get(0).getDigest());
                adapter.setDatas(data);
            }
        }, new NetListener.OnError() {
            @Override
            public void onError() {
                Log.d("DiscoverAdapter2221221", "获取数据失败了");
            }
        });

        holder.listView.setAdapter(adapter);
        return convertView;
    }
    class ViewHolder{
        TextView dateTv;
        ListView listView;

        public ViewHolder(View view){
            dateTv = (TextView) view.findViewById(R.id.discover_date);
            listView = (ListView) view.findViewById(R.id.discover_detail_list);
        }
    }
}
