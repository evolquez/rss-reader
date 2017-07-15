package com.oletob.rssreader.model;

import java.util.Date;

/**
 * Created by evolquez on 7/15/17.
 */

public class FeedHolder {
    public String title;
    public String imageUrl;
    public String link;
    public Date date;

    public FeedHolder(String title, String link, Date date, String imgUrl){
        this.title      = title;
        this.link       = link;
        this.date       = date;
        this.imageUrl   = imgUrl;
    }
}
