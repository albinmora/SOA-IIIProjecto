package com.example.albin.sportec.networking;

import android.app.Application;
import android.content.Context;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by raulm on 4/17/18.
 */

public class RestClientNews {

    private static final String SERVER = "http://172.18.172.185:4000";
    private Application mApplication;

    private RestClientNews(Application application) {
        this.mApplication = application;
    }

    public static RestClientNews with(Context application) {
        return new RestClientNews((Application) application);
    }

    public void getNews(FutureCallback<JsonObject> callback) {
        Ion.with(mApplication)
                .load(SERVER + "/news/")
                .asJsonObject()
                .setCallback(callback);
    }

    public void getAllNews(FutureCallback<JsonObject> callback) {
        Ion.with(mApplication)
                .load(SERVER + "/news/getAllNews")
                .asJsonObject()
                .setCallback(callback);
    }

    public void getDayNew(FutureCallback<JsonObject> callback) {
        Ion.with(mApplication)
                .load(SERVER + "/news/dayNew")
                .asJsonObject()
                .setCallback(callback);
    }

    public void getNew(String id, FutureCallback<JsonObject> callback) {
        Ion.with(mApplication)
                .load(SERVER + "/news/getNew?id=" + id)
                .asJsonObject()
                .setCallback(callback);
    }


}
