package com.lanou.bestbeautifulthings.discover.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.discover.beans.OthersBean;
import com.lanou.bestbeautifulthings.discover.discoverdetail.DiscoverDetailActivity;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/7/30.
 */
public class OthersAdapter extends RecyclerView.Adapter<OthersAdapter.mViewHolder> {
    private OthersBean othersBean;
    private Context context;

    public OthersAdapter(Context context) {
        this.context = context;
    }

    public void setBean(OthersBean bean) {
        this.othersBean = bean;

        notifyDataSetChanged();
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mViewHolder holder = null;
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_others_item, parent, false);
        holder = new mViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, final int position) {

        if (othersBean.getData().getProducts().get(position).getCover_images().size()>0){

        Picasso.with(context).load(othersBean.getData().getProducts().get(position).getCover_images().get(0)).resize(480,
                320).into(holder.coverIv);
        }else {
            Picasso.with(context).load(othersBean.getData().getProducts().get(position).getImages().get(0)).resize(480,
                    320).into(holder.coverIv);
        }
        holder.coverIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DiscoverDetailActivity.class);
                String id = String.valueOf(othersBean.getData().getProducts().get(position).getId());
                intent.putExtra("id",id);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return othersBean.getData().getProducts().size();
    }

    public class mViewHolder extends RecyclerView.ViewHolder{
        ImageView coverIv;
        public mViewHolder(View itemView) {
            super(itemView);
            coverIv = (ImageView) itemView.findViewById(R.id.item_others_iv);
        }
    }
}
