package com.zangfengshun.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;


public class NewsDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_display);
        Intent intent = getIntent();
        final ArrayList<NewsItem> newsDisplay = (ArrayList<NewsItem>) intent.getSerializableExtra("news");

        ListView listView = (ListView) findViewById(R.id.list);
        NewsItemAdapter adapter = new NewsItemAdapter(this, newsDisplay);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                NewsItem currentNewsItem = newsDisplay.get(position);
                Uri webpage = Uri.parse(currentNewsItem.getWebUrl());
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, webpage);
                if (browserIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(browserIntent);
                }
            }
        });
    }

    private class NewsItemAdapter extends ArrayAdapter<NewsItem> {

        public NewsItemAdapter(Context context, ArrayList<NewsItem> objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View listItemView = convertView;
            if (listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            }
            NewsItem currentNewsItem = getItem(position);
            TextView newsTitle = (TextView) listItemView.findViewById(R.id.news_title);
            newsTitle.setText(currentNewsItem.getTitle());

            TextView newsDate = (TextView) listItemView.findViewById(R.id.news_date);
            newsDate.setText(currentNewsItem.getPublicationDate());

            return listItemView;
        }
    }
}
