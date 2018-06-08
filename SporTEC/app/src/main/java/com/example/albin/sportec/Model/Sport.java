package com.example.albin.sportec.Model;

public class Sport {
    private String _id;
    private String sport;

    public Sport(String _id, String sport) {
        this._id = _id;
        this.sport = sport;
    }

    public Sport() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }
}
