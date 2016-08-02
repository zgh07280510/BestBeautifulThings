package com.lanou.bestbeautifulthings.discover.discovermain;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/7/30.
 */
public class DiscoverMainFragment extends BaseFragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> data;
    private DiscoverMainFragmentAdapter adapter;
    @Override
    protected int setLayout() {
        return R.layout.fragment_main_discover;
    }

    @Override
    protected void initView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.discover_fragment_viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.discover_fragment_tab);
        data = new ArrayList<>();
        adapter = new DiscoverMainFragmentAdapter(getChildFragmentManager());

    }

    @Override
    protected void initData() {

        data.add(new YouWuFragment());
        data.add(new BagFragment());
        data.add(new ShoesFragment());
        data.add(new JewelryFragment());
        data.add(new OrnamentFragmnet());
        data.add(new OthersFragmnet());
        adapter.setData(data);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
