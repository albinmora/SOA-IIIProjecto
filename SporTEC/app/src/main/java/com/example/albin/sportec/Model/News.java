package com.example.albin.sportec.Model;

public class News {

    // Members variables
    private String _id;
    private String title;
    private String date;
    private String body;
    private String image;
    private int dayNews;
    private String _idSport;

    //Getters

    //Constructor
    public News(String pId, int pDayNews, String pTitle, String pDate, String pDody, String pImage) {
        this._id = pId;
        this.dayNews = pDayNews;
        this.title = pTitle;
        this.date = pDate;
        this.body = pDody;
        this.image = pImage;
    }

    public News() {

    }

    public int getDayNews() {
        return dayNews;
    }

    public String getId() {
        return _id;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getBody() {
        return body;
    }

    public String get_idSport() {
        return _idSport;
    }

    public void set_idSport(String _idSport) {
        this._idSport = _idSport;
    }


}
