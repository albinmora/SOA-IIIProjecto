package com.example.albin.sportec;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.albin.sportec.Model.News;
import com.example.albin.sportec.Model.ResponseNews;
import com.example.albin.sportec.networking.RestClientNews;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class NewsDetailActivity extends AppCompatActivity {

    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private News mNews;
    private Gson mGson;

    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_detail);


        String newsId = getIntent().getStringExtra(MainActivity.PRODUCT_ID);

        RestClientNews
                .with(getApplicationContext())
                .getNew(newsId,new FutureCallback<JsonObject>() {

                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        mGson = new Gson();
                        mNews = mGson.fromJson(result.toString(), News.class);

                        TextView txtApp = (TextView) findViewById(R.id.txtAppName);
                        txtApp.setText(getString(R.string.app_name));
                        TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
                        txtTitle.setText(mNews.getTitle());

                        TextView txtContent = (TextView) findViewById(R.id.txtContent);
                        txtContent.setText(mNews.getBody());
                        ImageView iv = (ImageView) findViewById(R.id.imgDetail);
                        Bitmap bitmap = getBitmapFromAsset(mNews.getImage());
                        iv.setImageBitmap(bitmap);

                    }
                });

    }

    private Bitmap getBitmapFromAsset(String pImg) {
        AssetManager assetManager = NewsDetailActivity.this.getAssets();
        InputStream stream = null;

        try {
            stream = assetManager.open(pImg);
            return BitmapFactory.decodeStream(stream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
