package com.lanou.bestbeautifulthings.magazine;

/**
 * Created by zouguohua on 16/7/27.
 */
public class Magazineoverbean {
    private  String imgUrl;
    private  String Title;
    private String content;

    public Magazineoverbean() {
    }

    public Magazineoverbean(String content, String imgUrl, String title) {
        this.content = content;
        this.imgUrl = imgUrl;
        Title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
