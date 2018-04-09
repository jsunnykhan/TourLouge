package com.example.ltnull.hur.PojoClass;

import android.widget.ImageView;

import java.util.ArrayList;

public class HomePojo {
    private String image;
    private String name , time , news ;
    private int id;

    public HomePojo(int id ,String image, String name, String time, String news) {
        this.image = image;
        this.name = name;
        this.time = time;
        this.news = news;
        this.id = id ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

}
