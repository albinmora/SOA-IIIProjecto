package com.example.albin.sportec.Model;

import java.util.List;

public class ResponseNews {
    List<News> New;
    News NewO;
    int status;
    String message;

    public ResponseNews(List<News> aNew, int status, String message, News NewO) {
        New = aNew;
        this.NewO = NewO;
        this.status = status;
        this.message = message;
    }
    public ResponseNews(){

    }

    public List<News> getNew() {
        return New;
    }

    public void setNew(List<News> aNew) {
        New = aNew;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public News getNewO() {
        return NewO;
    }

    public void setNewO(News newO) {
        NewO = newO;
    }
}
