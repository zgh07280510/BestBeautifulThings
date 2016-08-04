package com.lanou.bestbeautifulthings.designer.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.designer.SingleLiteOrm;
import com.lanou.bestbeautifulthings.designer.bean.DesignerAttentionBean;
import com.lanou.bestbeautifulthings.designer.bean.DesignerBean;
import com.lanou.bestbeautifulthings.designer.eventbus.TvStateEventBus;
import com.lanou.bestbeautifulthings.designer.interfaces.AttentionClick;
import com.lanou.bestbeautifulthings.discover.beans.CommentBean;
import com.litesuits.orm.db.assit.QueryBuilder;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.carbs.android.avatarimageview.library.AvatarImageView;
import cn.sharesdk.framework.Platform;

/**
 * Created by dllo on 16/7/26.
 */
public class DesignerAdapter extends BaseAdapter {
    private Context context;
    private DesignerBean designerBean;
    private int checked, check;
    private AttentionClick attentionClick;
    private DesignerViewHolder holder;

    public void setAttentionClick(AttentionClick attentionClick) {
        this.attentionClick = attentionClick;
    }

    public DesignerAdapter(Context context) {
        this.context = context;
        EventBus.getDefault().register(this);
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
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.designer_gridview_item_list, parent, false);
            holder = new DesignerViewHolder(convertView);
            convertView.setTag(holder);

        } else {
            holder = (DesignerViewHolder) convertView.getTag();
        }
        holder.setPos(position);
        holder.tvRecommend.setText(designerBean.getData().getDesigners().get(position).getName());
        holder.tvDesigner.setText(designerBean.getData().getDesigners().get(position).getLabel());
        Glide.with(context).load(designerBean.getData().getDesigners().get(position).getRecommend_images().get(0)).into(holder.ivRecommend);
        Glide.with(context).load(designerBean.getData().getDesigners().get(position).getAvatar_url()).centerCrop().crossFade().into(holder.aivDesigner);

        holder.tvAttention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getPos();
                attentionClick.onClick(pos);
                holder.tvAttention.setText("已关注");
            }
        });

        return convertView;
    }

    class DesignerViewHolder {
        ImageView ivRecommend;
        TextView tvDesigner, tvRecommend, tvAttention;
        AvatarImageView aivDesigner;
        int pos;

        public int getPos() {
            return pos;
        }

        public void setPos(int pos) {
            this.pos = pos;
        }

        public DesignerViewHolder(View itemView) {
            ivRecommend = (ImageView) itemView.findViewById(R.id.iv_recommend_big);
            aivDesigner = (AvatarImageView) itemView.findViewById(R.id.iv_avatar);
            tvDesigner = (TextView) itemView.findViewById(R.id.tv_originator);
            tvRecommend = (TextView) itemView.findViewById(R.id.tv_recommend);
            tvAttention = (TextView) itemView.findViewById(R.id.tv_attention);

        }

        public void getState(TvStateEventBus tvStateEventBus) {
            checked = tvStateEventBus.getStateChecked();
            check = tvStateEventBus.getState();
        }
    }

}
