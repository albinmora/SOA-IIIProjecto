package com.example.albin.sportec.Model;

public class UserSport {
    String _idUser;
    String _idSport;

    public UserSport() {

    }

    public UserSport(String _idUser, String _idSport) {
        this._idUser = _idUser;
        this._idSport = _idSport;
    }

    public String get_idUser() {
        return _idUser;
    }

    public void set_idUser(String _idUser) {
        this._idUser = _idUser;
    }

    public String get_idSport() {
        return _idSport;
    }

    public void set_idSport(String _idSport) {
        this._idSport = _idSport;
    }
}
