package org.brohede.marcus.sqliteapp;

public class Mountain {
    private int id;
    private String name;
    private int height;
    private String location;
    private String imgURL;
    private String articleURL;

    public Mountain(int id, String name, int height, String location, String imgURL, String articleURL) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.location = location;
        this.imgURL = imgURL;
        this.articleURL = articleURL;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public String getLocation() {
        return location;
    }

    public String getImgURL() {
        return imgURL;
    }

    public String getArticleURL() {
        return articleURL;
    }

    public String getInfo() {
        return name + " is part of the " + location + " mountain range and is " + height + "m high.";
    }
}
