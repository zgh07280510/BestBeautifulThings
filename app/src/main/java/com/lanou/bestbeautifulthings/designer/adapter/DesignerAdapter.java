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
import com.litesuits.orm.db.assit.QueryBuilder;

import java.util.List;

import cn.carbs.android.avatarimageview.library.AvatarImageView;

/**
 * Created by dllo on 16/7/26.
 */
public class DesignerAdapter extends BaseAdapter {
    private Context context;
    private DesignerBean designerBean;
    private int id;
    private String s;

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
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
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

       id = designerBean.getData().getDesigners().get(position).getId();
        holder.tvAttention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QueryBuilder<DesignerAttentionBean> queryBuilder = new QueryBuilder<DesignerAttentionBean>(DesignerAttentionBean.class);
                queryBuilder.whereEquals("id", id);
                if (SingleLiteOrm.getSingleLiteOrm().getLiteOrm().query(queryBuilder).size() == 0) {
                    DesignerAttentionBean bean = new DesignerAttentionBean();
                    bean.setId(id);
                    bean.setAvatar(designerBean.getData().getDesigners().get(position).getAvatar_url());
                    bean.setLabel(designerBean.getData().getDesigners().get(position).getLabel());
                    bean.setName(designerBean.getData().getDesigners().get(position).getName());
                    bean.setRecommendImage(designerBean.getData().getDesigners().get(position).getRecommend_images().get(0));
                    SingleLiteOrm.getSingleLiteOrm().getLiteOrm().insert(bean);


                }
            }
        });
        return convertView;
    }

    class DesignerViewHolder {
        ImageView ivRecommend;
        TextView tvDesigner, tvRecommend, tvAttention;
        AvatarImageView aivDesigner;

        public DesignerViewHolder(View itemView) {
            ivRecommend = (ImageView) itemView.findViewById(R.id.iv_recommend_big);
            aivDesigner = (AvatarImageView) itemView.findViewById(R.id.iv_avatar);
            tvDesigner = (TextView) itemView.findViewById(R.id.tv_originator);
            tvRecommend = (TextView) itemView.findViewById(R.id.tv_recommend);
            tvAttention = (TextView) itemView.findViewById(R.id.tv_attention);

        }
    }
}
