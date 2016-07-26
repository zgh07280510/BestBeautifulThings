package com.lanou.bestbeautifulthings.magazine;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Toast;

import com.appeaser.deckview.views.DeckChildView;
import com.appeaser.deckview.views.DeckView;
import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.base.BaseFragment;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zouguohua on 16/7/26.
 */
public class MagazineFragment extends BaseFragment {


    DeckView<MagazineBean> mDeckView;
    Drawable mDefaultHeaderIcon;

    ArrayList<MagazineBean> mEntries;

    Bitmap mDefaultThumbnail;

    int scrollToChildIndex = -1, imageSize = 500;

    final String CURRENT_SCROLL = "current.scroll", CURRENT_LIST = "current.list";


    @Override
    protected int setLayout() {
        return R.layout.fragment_magazine;
    }

    @Override
    protected void initView(View view) {
        mDeckView = (DeckView) view.findViewById(R.id.deckview);

    }

    @Override
    protected void initData() {
        mDefaultThumbnail = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        //  mDefaultHeaderIcon=getResources().getDrawable(R)
        if (mEntries == null) {
            mEntries = new ArrayList<>();
            for (int i = 0; i < mEntries.get(0).getData().getArticles().size(); i++) {
                MagazineBean bean = new MagazineBean();
           

            }
        }

        DeckView.Callback<MagazineBean> deckViewCallback = new DeckView.Callback<MagazineBean>() {

            @Override
            public ArrayList<MagazineBean> getData() {
                return mEntries;
            }

            @Override
            public void loadViewData(WeakReference<DeckChildView<MagazineBean>> dcv, MagazineBean item) {

            }

            @Override
            public void unloadViewData(MagazineBean item) {

            }

            @Override
            public void onViewDismissed(MagazineBean item) {
                mEntries.remove(item);
                mDeckView.notifyDataSetChanged();
            }

            @Override
            public void onItemClick(MagazineBean item) {

            }

            @Override
            public void onNoViewsToDeck() {
                Toast.makeText(context, "没有图了", Toast.LENGTH_SHORT).show();
            }
        };
        mDeckView.initialize(deckViewCallback);
        if (scrollToChildIndex != -1) {
            mDeckView.post(new Runnable() {
                @Override
                public void run() {
                    mDeckView.scrollToChild(scrollToChildIndex);
                }
            });
        }
    }

}
