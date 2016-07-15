package com.zangfengshun.newsapp;

import java.io.Serializable;

/**
 * Created by Zang on 2016-07-14.
 * This class is used to store basic information of news.
 */
public class NewsItem implements Serializable {
    private String mTitle;
    private String mPublicationDate;
    private String mWebUrl;

    public NewsItem(String title, String publicationDate, String webUrl) {
        mTitle = title;
        mPublicationDate = publicationDate;
        mWebUrl = webUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getPublicationDate() {
        return mPublicationDate;
    }

    public String getWebUrl() {
        return mWebUrl;
    }
}
