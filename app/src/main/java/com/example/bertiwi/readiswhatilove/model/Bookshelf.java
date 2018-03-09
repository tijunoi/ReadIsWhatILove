package com.example.bertiwi.readiswhatilove.model;

import java.util.ArrayList;

/**
 * Created by Nil Ordoñez on 7/3/18.
 */

public class Bookshelf {
    private String title;
    private ArrayList<Book> mBookArrayList;

    public Bookshelf(String title, ArrayList<Book> bookArrayList) {
        this.title = title;
        mBookArrayList = bookArrayList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Book> getBookArrayList() {
        return mBookArrayList;
    }

    public void setBookArrayList(ArrayList<Book> bookArrayList) {
        mBookArrayList = bookArrayList;
    }
}
