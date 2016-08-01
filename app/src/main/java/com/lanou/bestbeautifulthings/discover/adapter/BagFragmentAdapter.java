package com.lanou.bestbeautifulthings.discover.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.discover.beans.BagBean;
import com.lanou.bestbeautifulthings.discover.discoverdetail.DiscoverDetailActivity;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/7/30.
 */
public class BagFragmentAdapter extends RecyclerView.Adapter<BagFragmentAdapter.mViewHolder> {
    private BagBean bagBean;
    private Context context;


    public BagFragmentAdapter(Context context) {
        this.context = context;
    }

    public void setBagBean(BagBean bagBean) {
        this.bagBean = bagBean;
        notifyDataSetChanged();
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mViewHolder holder = null;
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_bag_item,parent,false);
        holder = new mViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, final int position) {

        if (bagBean.getData().getProducts().get(position).getCover_images().size() >0){
        Picasso.with(context).load(bagBean.getData().getProducts().get(position).getCover_images().get(0)).resize(480,320).into(holder.coverIv);
        }else {
           Picasso.with(context).load(bagBean.getData().getProducts().get(position).getImages().get(0)).resize(480,
                   320).into(holder.coverIv);
        }
        holder.coverIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DiscoverDetailActivity.class);
                String id = String.valueOf(bagBean.getData().getProducts().get(position).getId());
                intent.putExtra("id",id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bagBean.getData().getProducts().size();
    }

    public class mViewHolder extends RecyclerView.ViewHolder{
        ImageView coverIv;
        public mViewHolder(View itemView) {
            super(itemView);
            coverIv = (ImageView) itemView.findViewById(R.id.item_bag_iv);
        }
    }
}
