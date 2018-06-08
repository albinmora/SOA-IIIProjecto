package com.example.albin.sportec.networking;

import android.app.Application;
import android.content.Context;

import com.example.albin.sportec.Model.Login;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.gson.GsonArrayParser;
import com.koushikdutta.ion.gson.GsonParser;

/**
 * Created by raulm on 4/17/18.
 */

public class RestClientNews {

    private Application mApplication;
    private static final String SERVER = "http://172.18.138.42:4000";

    public static RestClientNews with(Context application)
    {
        return new RestClientNews((Application) application);
    }

    private RestClientNews(Application application) {
        this.mApplication = application;
    }

    public void getNews(FutureCallback<JsonObject> callback) {
        Ion.with(mApplication)
                .load(SERVER + "/news/")
                .asJsonObject()
                .setCallback(callback);
    }

    public void getDayNew(FutureCallback<JsonObject> callback) {
        Ion.with(mApplication)
                .load(SERVER + "/news/dayNew")
                .asJsonObject()
                .setCallback(callback);
    }

    public void getNew(String id,FutureCallback<JsonObject> callback) {
        Ion.with(mApplication)
                .load(SERVER + "/news/getNew?id="+id)
                .asJsonObject()
                .setCallback(callback);
    }



}
