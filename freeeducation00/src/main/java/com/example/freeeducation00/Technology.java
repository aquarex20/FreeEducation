package com.example.freeeducation00;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;

public class Technology implements Parcelable {
    private String title;
    private String description;
    private Long image;

    public Technology(String title, String description, Long image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public Technology(HashMap technology) {
        this.title = (String) technology.get("title");
        this.description = (String) technology.get("description");
        this.image = (Long) technology.get("image");
    }

    protected Technology(Parcel in) {
        title = in.readString();
        description = in.readString();
        image = in.readLong();
    }

    public static final Creator<Technology> CREATOR = new Creator<Technology>() {
        @Override
        public Technology createFromParcel(Parcel in) {
            return new Technology(in);
        }

        @Override
        public Technology[] newArray(int size) {
            return new Technology[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getImage() {
        return image;
    }

    public void setImage(Long image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeLong(image);
    }
}
