package com.example.liushichao.myapplication.mode;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liushichao on 2017/11/30.
 */

public class WebLinkBean implements Parcelable{
    private String title;
    private String url;
    private String time;
    private String type;

    public WebLinkBean(String title, String url,String time,String type) {
        this.title = title;
        this.url = url;
        this.time=time;
        this.type=type;
    }

    public WebLinkBean() {
    }

    protected WebLinkBean(Parcel in) {
        title = in.readString();
        url = in.readString();
        time = in.readString();
        type = in.readString();
    }

    public static final Creator<WebLinkBean> CREATOR = new Creator<WebLinkBean>() {
        @Override
        public WebLinkBean createFromParcel(Parcel in) {
            return new WebLinkBean(in);
        }

        @Override
        public WebLinkBean[] newArray(int size) {
            return new WebLinkBean[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(url);
        parcel.writeString(time);
        parcel.writeString(type);
    }
}
