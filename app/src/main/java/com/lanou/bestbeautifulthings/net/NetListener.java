package com.lanou.bestbeautifulthings.net;

/**
 * Created by dllo on 16/7/26.
 */
public interface NetListener<T> {
    interface OnSucceed<T> {
        void OnSucceed(T result);

    }

    interface OnError {
        void onError();
    }

}
