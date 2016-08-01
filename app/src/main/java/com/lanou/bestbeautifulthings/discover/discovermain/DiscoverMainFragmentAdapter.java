package com.lanou.bestbeautifulthings.discover.discovermain;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dllo on 16/7/30.
 */
public class DiscoverMainFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> data;
    private String[] title = {"有物","包袋","鞋履","首饰","配饰","其他"};

    public void setData(List<Fragment> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public DiscoverMainFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
