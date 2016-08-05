package com.lanou.bestbeautifulthings.magazine;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.picasso.Target;


/**
 * Created by zouguohua on 16/7/26.
 */
public class Datum implements Parcelable{
    private int id;
    private String headerTitle, link;
    public Target target;
    private String Content;
    private String image_url;
    private  String name;
    private String label;//创始人
    private  String sub_title;

    public Datum() {
    }

    public Datum(String label, String content, String headerTitle, int id, String image_url, String link, String name, String sub_title, Target target) {
        this.label = label;
        Content = content;
        this.headerTitle = headerTitle;
        this.id = id;
        this.image_url = image_url;
        this.link = link;
        this.name = name;
        this.sub_title = sub_title;
        this.target = target;
    }

    protected Datum(Parcel in) {
        id = in.readInt();
        headerTitle = in.readString();
        link = in.readString();
        Content = in.readString();
        image_url = in.readString();
        name = in.readString();
        label = in.readString();
        sub_title = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(headerTitle);
        dest.writeString(link);
        dest.writeString(Content);
        dest.writeString(image_url);
        dest.writeString(name);
        dest.writeString(label);
        dest.writeString(sub_title);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Datum> CREATOR = new Creator<Datum>() {
        @Override
        public Datum createFromParcel(Parcel in) {
            return new Datum(in);
        }

        @Override
        public Datum[] newArray(int size) {
            return new Datum[size];
        }
    };

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }
}
