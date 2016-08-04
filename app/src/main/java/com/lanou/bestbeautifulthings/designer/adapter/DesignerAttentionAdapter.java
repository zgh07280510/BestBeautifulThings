package com.lanou.bestbeautifulthings.designer.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.designer.SingleLiteOrm;
import com.lanou.bestbeautifulthings.designer.bean.DesignerAttentionBean;
import com.litesuits.orm.db.assit.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import cn.carbs.android.avatarimageview.library.AvatarImageView;

/**
 * Created by dllo on 16/8/2.
 */
public class DesignerAttentionAdapter  extends BaseAdapter {
    private Context context;
    private ArrayList<DesignerAttentionBean> designerAttentionBean;
private  int id;
    public DesignerAttentionAdapter(Context context) {
        this.context = context;
    }

    public void setDesignerAttentionBean(ArrayList<DesignerAttentionBean> designerAttentionBean) {
        this.designerAttentionBean = designerAttentionBean;
        Log.d("DesignerAttentionAdapte", "designerAttentionBean.size():" + designerAttentionBean.size());
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return designerAttentionBean!=null&&designerAttentionBean.size()>0?designerAttentionBean.size():0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DesignerAttentionViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.designer_attention_item_list, parent, false);
            holder = new DesignerAttentionViewHolder(convertView);
            convertView.setTag(holder);

        } else {
            holder = (DesignerAttentionViewHolder) convertView.getTag();
        }
        id = designerAttentionBean.get(position).getId();
        Glide.with(context).load(designerAttentionBean.get(position).getRecommendImage()).into(holder.ivRecommend);
        Glide.with(context).load(designerAttentionBean.get(position).getAvatar()).centerCrop().crossFade().into(holder.aivDesigner);

        holder.tvDesigner.setText(designerAttentionBean.get(position).getName());
        holder.tvRecommend.setText(designerAttentionBean.get(position).getLabel());
        return convertView;
    }
    class DesignerAttentionViewHolder {
        ImageView ivRecommend;
        TextView tvDesigner, tvRecommend;
        AvatarImageView aivDesigner;

        public DesignerAttentionViewHolder(View itemView) {
            ivRecommend = (ImageView) itemView.findViewById(R.id.iv_designer_recommend_big);
            aivDesigner = (AvatarImageView) itemView.findViewById(R.id.iv_designer_avatar);
            tvDesigner = (TextView) itemView.findViewById(R.id.tv_designer_originator);
            tvRecommend = (TextView) itemView.findViewById(R.id.tv_designer_recommend);

        }
    }
}
