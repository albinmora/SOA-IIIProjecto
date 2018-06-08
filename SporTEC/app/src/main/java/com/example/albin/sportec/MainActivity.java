package com.example.albin.sportec;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.albin.sportec.Model.NavBarItem;
import com.example.albin.sportec.Model.News;
import com.example.albin.sportec.Model.ResponseNews;
import com.example.albin.sportec.Model.ResponseSport;
import com.example.albin.sportec.Model.Sport;
import com.example.albin.sportec.networking.RestClientNews;
import com.example.albin.sportec.networking.RestClientSport;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    static FirebaseDatabase database = FirebaseDatabase.getInstance();


    private static final String TAG = "MyActivity";
    public static final String PRODUCT_ID = "PRODUCT_ID";
    public static final String SEARCH = "SEARCH";
    private String mNews_id;
    private ListView mListView;
    private List<News> newsList;
    private DrawerLayout drawer;
    private ImageView mDayNewsImage;
    private List<News> mNews;
    private Gson mGson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseReference myRef = database.getReference("News");

        //NewsListAdapter adapter = new NewsListAdapter(this, R.layout.list_item, products);
        mListView = (ListView) findViewById(R.id.listView);
        //lv.setAdapter(adapter);

        mDayNewsImage = (ImageView) findViewById(R.id.imgDayNews);

        String id = myRef.push().getKey();

        Button btnBuscar = (Button) findViewById(R.id.btnBuscar);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);

                EditText searchText = (EditText) findViewById(R.id.buscarEditText);
                intent.putExtra(SEARCH, searchText.getText().toString());
                startActivity(intent);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, NewsDetailActivity.class);

                News news = newsList.get(position);
                Log.w(TAG, "Position" + position + "news.id" + news.getId());
                intent.putExtra(PRODUCT_ID, news.getId());
                startActivity(intent);

            }
        });

        mDayNewsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewsDetailActivity.class);
                intent.putExtra(PRODUCT_ID, mNews_id);
                startActivity(intent);
            }
        });

        RestClientNews
                .with(getApplicationContext())
                .getNews(new FutureCallback<JsonObject>() {

                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        mGson = new Gson();
                        ResponseNews mNews = mGson.fromJson(result.toString(), ResponseNews.class);
                        newsList = mNews.getNew();
                        NewsListAdapter adapter = new NewsListAdapter(MainActivity.this, R.layout.list_item, newsList);
                        mListView.setAdapter(adapter);
                    }
                });

        RestClientNews
                .with(getApplicationContext())
                .getDayNew(new FutureCallback<JsonObject>() {

                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        mGson = new Gson();
                        ResponseNews mNews = mGson.fromJson(result.toString(), ResponseNews.class);

                        News dayNews = mNews.getNewO();
                        Bitmap bitmap = getBitmapFromAsset(dayNews.getImage());
                        mDayNewsImage.setImageBitmap(bitmap);

                        TextView title = (TextView) findViewById(R.id.textTitleP);
                        title.setText(dayNews.getTitle());

                        mNews_id = dayNews.getId();



                        //NewsListAdapter adapter = new NewsListAdapter(MainActivity.this, R.layout.list_item, mNews.getNew());
                        //mListView.setAdapter(adapter);
                    }
                });




       /** myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                GenericTypeIndicator<List<News>> genericTypeIndicator = new GenericTypeIndicator<List<News>>() {
                };

                newsList = dataSnapshot.getValue(genericTypeIndicator);

                News dayNews = getDayNew(newsList);
                Bitmap bitmap = getBitmapFromAsset(dayNews.getImage());
                dayNewsImage.setImageBitmap(bitmap);

                TextView title = (TextView) findViewById(R.id.textTitleP);
                title.setText(dayNews.getTitle());

                news_id = dayNews.getId();


                NewsListAdapter adapter = new NewsListAdapter(MainActivity.this, R.layout.list_item, newsList);
                mListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });**/


        inflateViews();


    }

    private List<News> getOtherNews(List<News> pList) {
        List<News> tempList = new ArrayList<News>();
        for (int i = 0; i < pList.size(); i++) {
            News temNews = pList.get(i);
            String body = temNews.getBody();
            String title = temNews.getTitle();
            if (temNews.getDayNews() == 0) {
                tempList.add(temNews);
            }
        }
        return tempList;
    }

    private News getDayNew(List<News> pList) {
        News temNews = new News();
        for (int i = 0; i < pList.size(); i++) {
            temNews = pList.get(i);
            int cd = temNews.getDayNews();
            if (cd == 1) {
                return temNews;
            }
        }
        return temNews;
    }

    private Bitmap getBitmapFromAsset(String pImg) {
        AssetManager assetManager = MainActivity.this.getAssets();
        InputStream stream = null;

        try {
            stream = assetManager.open(pImg);
            return BitmapFactory.decodeStream(stream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void inflateViews() {
        //placeHolderText = (TextView) findViewById(R.id.placeholder_text);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        setNavBarButtons();
    }

    ;

    private void setNavBarButtons() {
        for (NavBarItem item : NavBarItem.values()) {
            TextView itemView = (TextView) findViewById(item.getItemId());
            itemView.setOnClickListener(this);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (NavBarItem.fromViewId(view.getId())) {

            case MAIN:
                break;

            case SPORT:
                drawer.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(MainActivity.this, SportActivity.class);
                startActivity(intent);
                break;
            case HISTORY:
                drawer.closeDrawer(GravityCompat.START);
                Intent intentH = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intentH);
                break;
            case CHALLENGE:
                drawer.closeDrawer(GravityCompat.START);
                //accountMenu.setVisible(true);
                break;

            case LOGOUT:
                drawer.closeDrawer(GravityCompat.START);
                //accountMenu.setVisible(true);
                break;
        }
    }
}
