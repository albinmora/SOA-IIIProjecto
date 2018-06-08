package com.example.albin.sportec.DB;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.albin.sportec.Model.Login;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static android.content.ContentValues.TAG;

public class LoginDBAccess extends AsyncTask<String, Void, Void> {

    static Gson mGson = new Gson();
    JsonObject mJsonPost;
    String mResponse;
    public LoginDBAccess(Login pLogin){

        String jsonPost = mGson.toJson(pLogin);
        mJsonPost = (JsonObject) new JsonParser().parse(jsonPost);
        mResponse = "";
    }

    @Override
    protected Void doInBackground(String... params) {

        try {

            /*URL url2 = new URL("http://172.18.138.42:4000/login/");
            HttpURLConnection urlConnection2 = (HttpURLConnection) url2.openConnection();
            urlConnection2.setRequestMethod("GET");
            int j = urlConnection2.getResponseCode();*/


            // This is getting the url from the string we passed in
            URL url = new URL("http://192.168.137.230:4000/login/login");

            // Create the urlConnection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();


            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestMethod("POST");

            // Send the post body
            if (this.mJsonPost != null) {
                OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
                writer.write(mJsonPost.toString());
                writer.flush();
            }

            int statusCode = urlConnection.getResponseCode();

            if (statusCode ==  200) {

                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());

                mResponse = convertInputStreamToString(inputStream);

                // From here you can convert the string to JSON with whatever JSON parser you like to use
                // After converting the string to JSON, I call my custom callback. You can follow this process too, or you can implement the onPostExecute(Result) method
            } else {
                // Status code is not 200
                // Do something to handle the error
            }

        } catch (Exception e) {
            Log.d(TAG, e.getLocalizedMessage());
        }
        return null;
    }




    private String convertInputStreamToString(InputStream inputStream) throws IOException {
        InputStreamReader isr = null;
        BufferedReader br = null;
        String content;
        StringBuilder sb = new StringBuilder();
        isr = new InputStreamReader(inputStream);
        br = new BufferedReader(isr);

        while ((content = br.readLine()) != null) {
            sb.append(content);
        }

        isr.close();
        br.close();
        return sb.toString();
    }

}
