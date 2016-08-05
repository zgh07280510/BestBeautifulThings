package com.lanou.bestbeautifulthings.magazine.magazinedetail.magazineimage;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zouguohua on 16/8/4.
 */
public class SetImage {
    private static SetImage s;
    private List<String> urls;

    private SetImage() {
        urls = new ArrayList<>();
    }

    public static SetImage getInstance() {
        if (s == null) {
            synchronized (SetImage.class) {
                if (s == null) {
                    s = new SetImage();
                }
            }
        }
        return s;
    }

    public void addImageUrl(String imageUrl) {
        urls.add(imageUrl);

    }
    public void addImageUrl(String imgUrl,int pos){
        urls.add(pos,imgUrl);
    }

    public void clear(){
        urls.clear();
    }

    public List<String> getImageUrl() {
        return urls;

    }


}
