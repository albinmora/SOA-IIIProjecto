package com.example.albin.sportec.Model;

import java.util.List;

public class ResponseSport {
    List<Sport> sport;
    int status;
    String message;

    public ResponseSport(List<Sport> sports, int status, String message) {
        this.sport = sports;
        this.status = status;
        this.message = message;
    }

    public ResponseSport() {

    }

    public List<Sport> getSports() {
        return sport;
    }

    public void setSports(List<Sport> sports) {
        sport = sports;
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
}
