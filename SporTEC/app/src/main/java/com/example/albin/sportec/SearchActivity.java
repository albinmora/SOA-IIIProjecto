package com.example.albin.sportec;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.albin.sportec.Model.NavBarItem;
import com.example.albin.sportec.Model.News;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    static FirebaseDatabase database = FirebaseDatabase.getInstance();

    private ListView lv;
    private static final String TAG = "SearchActivitiy";
    private String stringSearch;
    public static final String PRODUCT_ID = "PRODUCT_ID";
    private List<News> newsList;
    private DrawerLayout drawer;
    private TextView placeHolderText;
    private MenuItem accountMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        DatabaseReference myRef = database.getReference("News");


        stringSearch = getIntent().getStringExtra(MainActivity.SEARCH);

        //NewsListAdapter adapter = new NewsListAdapter(this, R.layout.list_item, products);
        lv = (ListView) findViewById(R.id.listViewSearch);
        //lv.setAdapter(adapter);

        String id = myRef.push().getKey();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchActivity.this, NewsDetailActivity.class);

                News news = newsList.get(position);
                Log.w(TAG, "Position" + position + "news.id" + news.getId());
                intent.putExtra(PRODUCT_ID, news.getId());
                startActivity(intent);

            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                GenericTypeIndicator<List<News>> genericTypeIndicator = new GenericTypeIndicator<List<News>>() {
                };

                newsList = dataSnapshot.getValue(genericTypeIndicator);

                NewsListAdapter adapter = new NewsListAdapter(SearchActivity.this, R.layout.list_item, search(newsList, stringSearch));
                lv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


    }

    private List<News> search(List<News> pList, String pSearch) {
        List<News> tempList = new ArrayList<News>();
        for (int i = 0; i < pList.size(); i++) {
            News temNews = pList.get(i);
            String body = temNews.getBody();
            String title = temNews.getTitle();
            if ((body.toLowerCase().contains(pSearch.toLowerCase())) || (title.toLowerCase().contains(pSearch.toLowerCase()))) {
                tempList.add(temNews);
            }
        }
        return tempList;
    }

}
