package com.zangfengshun.booklistingapp;

import java.io.Serializable;

/**
 * Created by Zang on 2016-07-12.
 */
public class Item implements Serializable {
    private String mTitle;
    private String mAuthor;

    public  Item (String title, String author) {
        this.mTitle = title;
        this.mAuthor = author;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }
}
