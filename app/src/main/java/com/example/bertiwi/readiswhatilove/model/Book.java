package com.example.bertiwi.readiswhatilove.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Date;

/**
 * Created by Bertiwi on 28/02/2018.
 */

public class Book implements Parcelable{
    private int id;
    private String title;
    private String publisher;
    private String publishedDate;
    private String description;
    private Double averageRating;
    private String image;

    public Book(int id, String title, String publisher, String publishedDate, String description, Double averageRating, String image) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.description = description;
        this.averageRating = averageRating;
        this.image = image;
    }

    protected Book(Parcel in) {
        id = in.readInt();
        title = in.readString();
        publisher = in.readString();
        publishedDate = in.readString();
        description = in.readString();
        if (in.readByte() == 0) {
            averageRating = null;
        } else {
            averageRating = in.readDouble();
        }
        image = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(publisher);
        dest.writeString(publishedDate);
        dest.writeString(description);
        if (averageRating == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(averageRating);
        }
        dest.writeString(image);
    }
}
