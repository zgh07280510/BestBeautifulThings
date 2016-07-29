package com.lanou.bestbeautifulthings.discover;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.base.BaseFragment;
import com.lanou.bestbeautifulthings.net.NetListener;
import com.lanou.bestbeautifulthings.net.NetRequest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zouguohua on 16/7/26.
 */
public class DiscoverFragment extends BaseFragment {
    private ArrayList<String> data;
    private DiscoverAdapter adapter;
    private ListView listView;
    public static final String DF_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd";

    @Override
    protected int setLayout() {
        return R.layout.fragment_discover;
    }

    @Override
    protected void initView(View view) {
        listView = (ListView) view.findViewById(R.id.discover_list);

    }

    @Override
    protected void initData() {
        data = new ArrayList<>();
        adapter = new DiscoverAdapter(context);
        Date date = subDateTime(gainCurrentDate(), 24);
        data.add(String.valueOf(dateToLong(gainCurrentDate())));
        adapter.setDatas(data);
        listView.setAdapter(adapter);
        dateToLong(gainCurrentDate());

    }

    /**
     * 对日期进行相减操作
     *
     * @param target 需要进行运算的日期
     * @param hour   小时
     * @return
     */
    public static Date subDateTime(Date target, double hour) {
        if (null == target || hour < 0) {
            return target;
        }

        return new Date(target.getTime() - (long) (hour * 60 * 60 * 1000));
    }


    /**
     * 获取系统当前时间
     * @return
     */
    public static Date gainCurrentDate() {
        return new Date();
    }

    /**
     * 将data类型转换为long类型
     * @param date
     * @return
     */
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    /**
     * 将data类型转换为String类型
     * @param data
     * @param formatType
     * @return
     */
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

}
