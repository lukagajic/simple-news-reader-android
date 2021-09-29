package com.lgajic.primerrss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.lgajic.primerrss.adapter.NewsListAdapter;
import com.lgajic.primerrss.api.NewsApi;
import com.lgajic.primerrss.api.ReadDataHandler;
import com.lgajic.primerrss.model.NewsItem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private NewsListAdapter adapter;
    private List<NewsItem> newsItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
    }

    @SuppressLint("HandlerLeak")
    private void initComponents() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        newsItems = new ArrayList<>();

        NewsApi.getJSON(NewsApi.RSS_URL_JSON, new ReadDataHandler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                String response = getJson();

                Log.d(TAG, "Response: " + response);

                try {
                    JSONObject object = new JSONObject(response);
                    newsItems = NewsItem.parseJSONArray(object);

                    adapter = new NewsListAdapter(MainActivity.this, newsItems);
                    recyclerView.setAdapter(adapter);
                } catch (Exception e) {
                    // ...
                }
            }
        });
    }


}