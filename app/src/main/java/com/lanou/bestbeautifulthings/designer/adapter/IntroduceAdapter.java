package com.lanou.bestbeautifulthings.designer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.designer.bean.DesignerWorksBean;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by dllo on 16/7/28.
 */
public class IntroduceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private DesignerWorksBean data;
    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public IntroduceAdapter(Context context) {
        this.context = context;
    }

    public void setData(DesignerWorksBean data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public DesignerWorksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.introduce_recyclerview_item, parent, false);
        DesignerWorksViewHolder holder = new DesignerWorksViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        DesignerWorksViewHolder holder1 = (DesignerWorksViewHolder) holder;
        Glide.with(context).load(data.getData().getProducts().get(position).getImages().get(0)).centerCrop().crossFade().into(holder1.imageView);
        ((DesignerWorksViewHolder) holder).layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getLayoutPosition();
                onClickListener.OnClick(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return data == null ? 0 : data.getData().getProducts().size();
       }


    public class DesignerWorksViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        LinearLayout layout;

        public DesignerWorksViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.iv_introduce_recycler);
            layout = (LinearLayout) view.findViewById(R.id.layout_recycler);
        }

    }

    public interface OnClickListener {
        void OnClick(int position);
    }
}
