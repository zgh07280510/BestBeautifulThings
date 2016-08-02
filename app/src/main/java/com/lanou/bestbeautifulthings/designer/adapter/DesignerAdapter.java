package com.lanou.bestbeautifulthings.designer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.designer.bean.DesignerBean;

import cn.carbs.android.avatarimageview.library.AvatarImageView;

/**
 * Created by dllo on 16/7/26.
 */
public class DesignerAdapter extends BaseAdapter {
    private Context context;
    private DesignerBean designerBean;

    public DesignerAdapter(Context context) {
        this.context = context;
    }

    public void setDesignerBean(DesignerBean designerBean) {
        this.designerBean = designerBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return designerBean != null && designerBean.getData().getDesigners().size() > 0 ? designerBean.getData().getDesigners().size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DesignerViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.designer_gridview_item_list, parent, false);
            holder = new DesignerViewHolder(convertView);
            convertView.setTag(holder);

        } else {
            holder = (DesignerViewHolder) convertView.getTag();
        }
        holder.tvRecommend.setText(designerBean.getData().getDesigners().get(position).getName());
        holder.tvDesigner.setText(designerBean.getData().getDesigners().get(position).getLabel());
        Glide.with(context).load(designerBean.getData().getDesigners().get(position).getRecommend_images().get(0)).into(holder.ivRecommend);
        Glide.with(context).load(designerBean.getData().getDesigners().get(position).getAvatar_url()).centerCrop().crossFade().into(holder.aivDesigner);


        return convertView;
    }
    class DesignerViewHolder {
        ImageView ivRecommend;
        TextView tvDesigner, tvRecommend;
        AvatarImageView aivDesigner;

        public DesignerViewHolder(View itemView) {
            ivRecommend = (ImageView) itemView.findViewById(R.id.iv_recommend_big);
            aivDesigner = (AvatarImageView) itemView.findViewById(R.id.iv_avatar);
            tvDesigner = (TextView) itemView.findViewById(R.id.tv_originator);
            tvRecommend = (TextView) itemView.findViewById(R.id.tv_recommend);
        }
    }
}
