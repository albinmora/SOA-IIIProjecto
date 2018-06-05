package com.example.albin.sportec;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.albin.sportec.Model.Sports;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class SportActivity extends AppCompatActivity {

    private List<Sports> gridItemsList;
    private GridView gridview;
    private static final String TAG = "SportActivity";

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


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                GenericTypeIndicator<List<Sports>> genericTypeIndicator = new GenericTypeIndicator<List<Sports>>() {
                };

                gridItemsList = dataSnapshot.getValue(genericTypeIndicator);

                GridViewAdapter customAdapter = new GridViewAdapter(SportActivity.this, gridItemsList);
                gridview.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }


}
