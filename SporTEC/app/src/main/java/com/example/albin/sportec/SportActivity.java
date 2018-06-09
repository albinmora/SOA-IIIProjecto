package com.example.albin.sportec;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.albin.sportec.Model.ResponseSport;
import com.example.albin.sportec.Model.Sport;
import com.example.albin.sportec.networking.RestClientSport;
import com.google.firebase.database.DatabaseReference;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;

import java.util.List;

public class SportActivity extends AppCompatActivity {

    private static final String TAG = "SportActivity";
    private List<Sport> mGridItemsList;
    private GridView gridview;
    private Gson mGson;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_screen);

        DatabaseReference myRef = MainActivity.database.getReference("Sports");

        gridview = (GridView) findViewById(R.id.gridviewSportScreen);


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SportActivity.this, "Position: " + position, Toast.LENGTH_SHORT).show();
            }
        });


        RestClientSport
                .with(getApplicationContext())
                .getAllSports(new FutureCallback<JsonObject>() {

                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        mGson = new Gson();
                        ResponseSport mNews = mGson.fromJson(result.toString(), ResponseSport.class);
                        mGridItemsList = mNews.getSports();

                        GridViewAdapter customAdapter = new GridViewAdapter(SportActivity.this, mGridItemsList);
                        gridview.setAdapter(customAdapter);

                    }
                });

    }


}
