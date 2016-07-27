package com.lanou.bestbeautifulthings.magazine;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.picasso.Target;


/**
 * Created by zouguohua on 16/7/26.
 */
public class Datum implements Parcelable {
    public int id;
    public String headerTitle, link;
    public Target target;


    public Datum() {

    }


    protected Datum(Parcel in) {
        id = in.readInt();
        headerTitle = in.readString();
        link = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(headerTitle);
        dest.writeString(link);
    }

    @Override
    public boolean equals(Object o) {
        return ((Datum) o).id == this.id;
    }
}
