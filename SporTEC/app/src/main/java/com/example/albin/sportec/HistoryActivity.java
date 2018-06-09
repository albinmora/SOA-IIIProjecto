package com.example.albin.sportec;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.albin.sportec.Model.History;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    public static final String PRODUCT_ID = "PRODUCT_ID";
    public static final String SEARCH = "SEARCH";
    private static final String TAG = "MyActivity";
    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private ListView lv;
    private Long news_id;
    private List<History> mHistoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        DatabaseReference myRef = database.getReference("History");


        lv = (ListView) findViewById(R.id.listViewHistorials);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                GenericTypeIndicator<List<History>> genericTypeIndicator = new GenericTypeIndicator<List<History>>() {
                };

                mHistoryList = dataSnapshot.getValue(genericTypeIndicator);

                HistoryListAdapter adapter = new HistoryListAdapter(HistoryActivity.this, R.layout.activity_history_item, mHistoryList);
                lv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }


}
