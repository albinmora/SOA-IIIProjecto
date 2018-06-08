package com.example.albin.sportec.networking;

import android.app.Application;
import android.content.Context;

import com.example.albin.sportec.Model.Login;
import com.example.albin.sportec.Model.UserSport;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.gson.GsonArrayParser;
import com.koushikdutta.ion.gson.GsonParser;

import java.util.List;

/**
 * Created by raulm on 4/17/18.
 */

public class RestClientSport {

    private Application mApplication;
    private static final String SERVER = "http://172.18.138.42:4000";

    public static RestClientSport with(Context application)
    {
        return new RestClientSport((Application) application);
    }

    private RestClientSport(Application application) {
        this.mApplication = application;
    }

    public void getAllSports( FutureCallback<JsonObject> callback) {
        Ion.with(mApplication)
                .load(SERVER + "/sport/")
                .asJsonObject()
                .setCallback(callback);
    }

    public void addSportToUser(UserSport pUserSport, FutureCallback<JsonObject> callback) {
        Gson gson = new Gson();
        String json = gson.toJson(pUserSport);
        GsonParser parser = new GsonArrayParser();
        JsonObject gObject =  (JsonObject) new JsonParser().parse(json);
        Ion.with(mApplication)
                .load(SERVER + "/sport/UserSport")
                .setJsonPojoBody(gObject)
                .asJsonObject()
                .setCallback(callback);
    }

}
