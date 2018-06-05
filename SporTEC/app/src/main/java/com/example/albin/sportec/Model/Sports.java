package com.example.albin.sportec.Model;

public class Sports{
    private String sport;
    private String image;
    private Long id;

    public Sports(Long pId,String content, String imageResource) {
        this.sport = content;
        this.id = pId;
        this.image = imageResource;
    }

    public Sports(){

    }

    public Long getId() {
        return id;
    }
    public String getSport() {
        return sport;
    }

    public String getImage() {
        return image;
    }


}
