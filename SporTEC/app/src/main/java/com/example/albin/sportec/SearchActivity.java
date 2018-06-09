package com.example.albin.sportec;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.albin.sportec.Model.News;
import com.example.albin.sportec.Model.ResponseNews;
import com.example.albin.sportec.networking.RestClientNews;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    //static FirebaseDatabase database = FirebaseDatabase.getInstance();

    public static final String PRODUCT_ID = "PRODUCT_ID";
    private static final String TAG = "SearchActivitiy";
    private ListView mListView;
    private String mStringSearch;
    private List<News> mNewsList;
    private Gson mGson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        //DatabaseReference myRef = database.getReference("News");


        mStringSearch = getIntent().getStringExtra(MainActivity.SEARCH);

        //NewsListAdapter adapter = new NewsListAdapter(this, R.layout.list_item, products);
        mListView = (ListView) findViewById(R.id.listViewSearch);
        //lv.setAdapter(adapter);

        //String id = myRef.push().getKey();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchActivity.this, NewsDetailActivity.class);

                News news = mNewsList.get(position);
                Log.w(TAG, "Position" + position + "news.id" + news.getId());
                intent.putExtra(PRODUCT_ID, news.getId());
                startActivity(intent);

            }
        });

        RestClientNews
                .with(getApplicationContext())
                .getAllNews(new FutureCallback<JsonObject>() {

                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        mGson = new Gson();
                        ResponseNews mNews = mGson.fromJson(result.toString(), ResponseNews.class);
                        mNewsList = mNews.getNew();

                        NewsListAdapter adapter = new NewsListAdapter(SearchActivity.this, R.layout.list_item, search(mNewsList, mStringSearch));
                        mListView.setAdapter(adapter);
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
