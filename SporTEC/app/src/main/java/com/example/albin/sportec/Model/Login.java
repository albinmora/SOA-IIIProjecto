package com.example.albin.sportec.Model;

public class Login {
    private String _id;
    private String user;
    private String password;


    public Login(String _id, String user, String password) {
        this._id = _id;
        this.user = user;
        this.password = password;
    }

    public Login() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
