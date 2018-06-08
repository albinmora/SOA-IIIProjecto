package com.example.albin.sportec.Model;

public class ResponseUser {
    Login User;
    int status;
    String message;

    public ResponseUser(Login user, int status, String message) {
        User = user;
        this.status = status;
        this.message = message;
    }

    public ResponseUser() {

    }

    public Login getUser() {
        return User;
    }

    public void setUser(Login user) {
        User = user;
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
