package com.zangfengshun.tourguideapp;

/**
 * Created by Zang on 2016-07-11.
 * This class stores the text and image information of items in tour app.
 */
public class Item {
    private String mName;
    private String mContactInfomation;
    private int mImageResourceId;
    private final static int DEFAULT_IMAGE_ID = -1;

    public Item(String name, String contactInfomation) {
        this.mName = name;
        this.mContactInfomation = contactInfomation;
        mImageResourceId = -1;
    }

    public Item(String name, String contactInformation, int imageResourceId){
        this.mName = name;
        this.mContactInfomation = contactInformation;
        this.mImageResourceId = imageResourceId;
    }

    public String getmName() {
        return mName;
    }

    public String getmContactInfomation() {
        return mContactInfomation;
    }

    public int getmImageResourceId() {
        return mImageResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != DEFAULT_IMAGE_ID;
    }

}
