package com.parse.starter.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by kelsiedong on 11/15/15.
 */
public class Item implements Parcelable {

    public String title;
    public String description;
    public int price;
    public java.util.Date createdAt;
    public java.util.Date updatedAt;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(price);
        dest.writeString(createdAt.toString());
        dest.writeString(updatedAt.toString());
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    private Item(Parcel in) {
        title = in.readString();
        description = in.readString();
        price = in.readInt();
        DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        try {
            createdAt = dateFormat.parse(in.readString());
            updatedAt = dateFormat.parse(in.readString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Item() {
    }
}
