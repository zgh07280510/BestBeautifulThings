package com.lanou.bestbeautifulthings.util;

/**
 * Created by dllo on 16/7/29.
 */
public interface OnRefreshListener {
    /**
     * 下拉刷新
     */
    void onDownPullRefresh();

    /**
     * 上拉加载更多
     */
    void onLoadingMore();
}
