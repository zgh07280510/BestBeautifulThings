package com.lanou.bestbeautifulthings.discover.discovermain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.discover.discovermain.DiscoverBean;
import com.lanou.bestbeautifulthings.util.XCRoundImageView;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by dllo on 16/7/29.
 */
public class DiscoverYouWuAdapter extends BaseAdapter  {
    private DiscoverBean datas;
    private Context context;

    public DiscoverYouWuAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(DiscoverBean datas) {

        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.getData().getActivities().size();


    }

    @Override
    public Object getItem(int position) {
        return datas.getData().getActivities().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DetailViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.discover_detail_item,parent,false);
            holder = new DetailViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (DetailViewHolder) convertView.getTag();
        }
        if (position == 0){
           String date =  GetStringFromLong(datas.getData().getActivities().get(position).getPublish_at());
            holder.dateTv.setVisibility(View.VISIBLE);
            holder.weekTv.setVisibility(View.VISIBLE);
            holder.weekTv.setText(getWeekByDateStr(date));
            holder.dateTv.setText(date  + " ,");
        }else {
            holder.dateTv.setVisibility(View.GONE);
            holder.weekTv.setVisibility(View.GONE);


        }

        holder.userlabelTv.setText(datas.getData().getActivities().get(position).getDesigner().getLabel());
        holder.userNameTv.setText(datas.getData().getActivities().get(position).getDesigner().getName());
        Picasso.with(context).load(datas.getData().getActivities().get(position).getDesigner().getAvatar_url()).resize(480,320).into(holder.userIv);
        Glide.with(context).load(datas.getData().getActivities().get(position).getImages().get(0)).into(holder.coverIv);
        holder.digestTv.setText(datas.getData().getActivities().get(position).getDigest());
        return convertView;
    }
    class DetailViewHolder{
        ImageView coverIv;
        TextView digestTv,dateTv,weekTv;
        XCRoundImageView userIv;
        TextView userNameTv;
        TextView userlabelTv;
        public DetailViewHolder(View view){
            coverIv = (ImageView) view.findViewById(R.id.discover_detail_cover_iv);
            digestTv = (TextView) view.findViewById(R.id.discover_digest);
            userIv = (XCRoundImageView) view.findViewById(R.id.user_image);
            userNameTv = (TextView) view.findViewById(R.id.user_name);
            userlabelTv = (TextView) view.findViewById(R.id.user_label);
            dateTv = (TextView) view.findViewById(R.id.discover_detail_date_tv);
            weekTv = (TextView) view.findViewById(R.id.discover_detail_week);
        }

    }
    public static String getWeekByDateStr(String strDate)
    {
        int year = Integer.parseInt(strDate.substring(0, 4));
        int month = Integer.parseInt(strDate.substring(5, 7));
        int day = Integer.parseInt(strDate.substring(8, 10));

        Calendar c = Calendar.getInstance();

        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, day);

        String week = "";
        int weekIndex = c.get(Calendar.DAY_OF_WEEK);

        switch (weekIndex)
        {
            case 1:
                week = "星期日";
                break;
            case 2:
                week = "星期一";
                break;
            case 3:
                week = "星期二";
                break;
            case 4:
                week = "星期三";
                break;
            case 5:
                week = "星期四";
                break;
            case 6:
                week = "星期五";
                break;
            case 7:
                week = "星期六";
                break;
        }
        return week;
    }
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }
    public static String GetStringFromLong(long millis)
    {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy.MM.dd");
        java.util.Date dt = new Date(millis);
        return sdf.format(dt);
    }
}
