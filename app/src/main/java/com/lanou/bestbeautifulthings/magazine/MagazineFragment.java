package com.lanou.bestbeautifulthings.magazine;

import android.content.Intent;
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
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.appeaser.deckview.views.DeckChildView;
import com.appeaser.deckview.views.DeckView;
import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.base.BaseFragment;
import com.lanou.bestbeautifulthings.magazine.magazinedetail.MagazineActivity;
import com.lanou.bestbeautifulthings.net.NetListener;
import com.lanou.bestbeautifulthings.net.NetRequest;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.greenrobot.eventbus.EventBus;

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
    ArrayList<Datum> mEntries;
    Bitmap mDefaultThumbnail;

    int scrollToChildIndex = -1;
    private FrameLayout frameLayout;

    @Override
    protected int setLayout() {
        return R.layout.fragment_magazine;
    }

    @Override
    protected void initView(View view) {

        frameLayout = (FrameLayout) view.findViewById(R.id.content);

    }

    @Override
    protected void initData() {

        if (mEntries == null) {
            mEntries = new ArrayList<>();
            NetRequest.getInstance().getMagazineBean(MagazineBean.class, new NetListener.OnSucceed<MagazineBean>() {
                @Override
                public void OnSucceed(MagazineBean result) {
                    for (int i = 0; i < result.getData().getArticles().size(); i++) {
                        Datum datum = new Datum();
                        datum.setLink(result.getData().getArticles().get(i).getImage_url());
                        datum.setId(generateUniqueKey());
                        String title = result.getData().getArticles().get(i).getTitle();
                        String subTitle = result.getData().getArticles().get(i).getSub_title();
                        datum.setHeaderTitle(title);
                        datum.setContent(result.getData().getArticles().get(i).getContent());
                        datum.setSub_title(subTitle);
                        mEntries.add(0, datum);
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
                            Picasso.with(context).cancelRequest(item.getTarget());
                        }

                        @Override
                        public void onViewDismissed(Datum item) {
                            mEntries.remove(item);
                            mDeckView.notifyDataSetChanged();
                        }

                        @Override
                        public void onItemClick(Datum item) {
                            Intent intent = new Intent(context, MagazineActivity.class);
                            intent.putExtra("magBean", item);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            //    context.startActivity(new Intent(context, MagazineActivity.class));
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

        }


    }

    void loadViewDataInternal(final Datum datum,
                              final WeakReference<DeckChildView<Datum>> weakView) {
        // datum.target can be null
        Picasso.with(context).cancelRequest(datum.getTarget());
        datum.target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                // Pass loaded Bitmap to view
                if (weakView.get() != null) {
                    weakView.get().onDataLoaded(datum, bitmap,
                            mDefaultHeaderIcon, datum.getHeaderTitle(), Color.WHITE);
                }
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                // Loading failed. Pass default thumbnail instead
                if (weakView.get() != null) {
                    weakView.get().onDataLoaded(datum, mDefaultThumbnail,
                            mDefaultHeaderIcon, datum.getHeaderTitle() + " Failed", Color.DKGRAY);
                }
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                // Pass the default thumbnail for now. It will
                // be replaced once the target Bitmap has been loaded
                if (weakView.get() != null) {
                    weakView.get().onDataLoaded(datum, mDefaultThumbnail,
                            mDefaultHeaderIcon, "Loading...", Color.BLUE);
                }
            }
        };

        // Begin loading
        Picasso.with(context).load(datum.getLink()).into(datum.getTarget());
    }

    private static int generateUniqueKey() {
        return ++KEY;
    }
}
