package com.lanou.bestbeautifulthings.magazine;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.appeaser.deckview.views.DeckChildView;
import com.appeaser.deckview.views.DeckView;
import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.base.BaseFragment;
import com.lanou.bestbeautifulthings.net.NetListener;
import com.lanou.bestbeautifulthings.net.NetRequest;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zouguohua on 16/7/26.
 */
public class MagazineFragment extends BaseFragment {

    private static int KEY = 0;
    DeckView<Datum> mDeckView;
    Drawable mDefaultHeaderIcon;
    private Magazineoverbean bean;
    private List<Magazineoverbean> beans;

    ArrayList<Datum> mEntries;

    Bitmap mDefaultThumbnail;

    int scrollToChildIndex = -1, imageSize = 500;
    private FrameLayout frameLayout;

    @Override
    protected int setLayout() {
        return R.layout.fragment_magazine;
    }

    @Override
    protected void initView(View view) {
        // mDeckView = (DeckView) view.findViewById(R.id.deckview);
        frameLayout = (FrameLayout) view.findViewById(R.id.content);
    }

    @Override
    protected void initData() {

        if (mEntries == null) {
            mEntries = new ArrayList<>();
            NetRequest.getInstance().getMagazineBean(MagazineBean.class, new NetListener.OnSucceed<MagazineBean>() {
                @Override
                public void OnSucceed(MagazineBean result) {
                    //  Toast.makeText(context, "请求成功", Toast.LENGTH_SHORT).show();
                    for (int i = result.getData().getArticles().size() - 1; i >= 0; i--) {
                        //     Log.d("MagazineFragment", "result.getData().getArticles().size():" + result.getData().getArticles().size());
                        Datum datum = new Datum();
                        datum.link = result.getData().getArticles().get(i).getImage_url();
                        datum.id = generateUniqueKey();
//                         Html.fromHtml("<font color=\"#ff0000\">红色</font>其它颜色");
                        String title = result.getData().getArticles().get(i).getTitle();
                        String subTitle = result.getData().getArticles().get(i).getSub_title();
                        //+ "\n" + subTitle;
                        datum.headerTitle = title;
                        mEntries.add(datum);
                    }

                    //  mDefaultThumbnail = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                    //       mDefaultHeaderIcon = getResources().getDrawable(R.mipmap.ic_launcher);
                    mDeckView = new DeckView<Datum>(getContext());
                    frameLayout.addView(mDeckView, -1, -1);
                    DeckView.Callback<Datum> deckViewCallback = new DeckView.Callback<Datum>() {

                        @Override
                        public ArrayList<Datum> getData() {
                            return mEntries;
                        }

                        @Override
                        public void loadViewData(final WeakReference<DeckChildView<Datum>> dcv, Datum item) {
//                try {
//                    Bitmap bitmap = Picasso.with(context).load(item.getData().getArticles().get(0).getImage_url()).get();
//                    dcv.get().onDataLoaded(item, bitmap, null, ite m.getData().getArticles().get(0).getTitle(), Color.WHITE);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                            loadViewDataInternal(item, dcv);
                        }

                        @Override
                        public void unloadViewData(Datum item) {
                            Picasso.with(context).cancelRequest(item.target);
                        }

                        @Override
                        public void onViewDismissed(Datum item) {
                            mEntries.remove(item);
                            mDeckView.notifyDataSetChanged();
                        }

                        @Override
                        public void onItemClick(Datum item) {

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
            }, new NetListener.OnError() {
                @Override
                public void onError() {
                    Toast.makeText(context, "请求失败", Toast.LENGTH_SHORT).show();
                }
            });
//            for (int i = 0; i < 100; i++) {
//                Datum datum = new Datum();
//                datum.id = generateUniqueKey();
//                datum.link = "http://lorempixel.com/" + imageSize + "/" + imageSize
//                        + "/sports/" + "ID " + datum.id + "/";
//                datum.headerTitle = "ImageID" + datum.id;
//                mEntries.add(datum);
//
//
//            }


        }


    }

    void loadViewDataInternal(final Datum datum,
                              final WeakReference<DeckChildView<Datum>> weakView) {
        // datum.target can be null
        Picasso.with(context).cancelRequest(datum.target);

        datum.target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                // Pass loaded Bitmap to view
                if (weakView.get() != null) {
                    weakView.get().onDataLoaded(datum, bitmap,
                            mDefaultHeaderIcon, datum.headerTitle, Color.WHITE);
                }
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                // Loading failed. Pass default thumbnail instead
                if (weakView.get() != null) {
                    weakView.get().onDataLoaded(datum, mDefaultThumbnail,
                            mDefaultHeaderIcon, datum.headerTitle + " Failed", Color.DKGRAY);
                }
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                // Pass the default thumbnail for now. It will
                // be replaced once the target Bitmap has been loaded
                if (weakView.get() != null) {
                    weakView.get().onDataLoaded(datum, mDefaultThumbnail,
                            mDefaultHeaderIcon, "Loading...", Color.DKGRAY);
                }
            }
        };

        // Begin loading
        Picasso.with(context).load(datum.link).into(datum.target);
    }

    private static int generateUniqueKey() {
        return ++KEY;
    }
}
