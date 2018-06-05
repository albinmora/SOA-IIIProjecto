package com.example.albin.sportec.Model;

public class News{

    // Members variables
    private Long id;
    private String title;
    private String date;
    private String body;
    private String image;
    private int dayNews;

    //Getters

    public int getDayNews() { return dayNews; }

    public Long getId() {
        return id;
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

    //Constructor
    public News(Long pId,int pDayNews,String pTitle, String pDate, String pDody, String pImage){
        this.id = pId;
        this.dayNews = pDayNews;
        this.title = pTitle;
        this.date = pDate;
        this.body = pDody;
        this.image = pImage;
    }

    public News(){

    }
}
