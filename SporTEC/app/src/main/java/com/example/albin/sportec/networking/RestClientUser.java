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

public class RestClientUser {

    private static final String SERVER = "http://172.18.172.185:4000";
    private Application mApplication;

    private RestClientUser(Application application) {
        this.mApplication = application;
    }

    public static RestClientUser with(Context application) {
        return new RestClientUser((Application) application);
    }

    public void loginUser(Login pLogin, FutureCallback<JsonObject> callback) {
        Gson gson = new Gson();
        String json = gson.toJson(pLogin);
        GsonParser parser = new GsonArrayParser();
        JsonObject gObject = (JsonObject) new JsonParser().parse(json);
        Ion.with(mApplication)
                .load(SERVER + "/login/login")
                .setJsonObjectBody(gObject)
                .asJsonObject()
                .setCallback(callback);
    }


    public void registerUser(Login pLogin, FutureCallback<JsonObject> callback) {
        Gson gson = new Gson();
        String json = gson.toJson(pLogin);
        GsonParser parser = new GsonArrayParser();
        JsonObject gObject = (JsonObject) new JsonParser().parse(json);
        Ion.with(mApplication)
                .load(SERVER + "/login/register")
                .setJsonObjectBody(gObject)
                .asJsonObject()
                .setCallback(callback);
    }

}
