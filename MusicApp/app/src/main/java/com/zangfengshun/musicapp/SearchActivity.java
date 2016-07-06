package com.zangfengshun.musicapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //This button adds selected music into my favorite list a
        Button button_add_to_list = (Button)findViewById(R.id.button_add_to_list);
        button_add_to_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, MyListActivity.class);
                startActivity(intent);
            }
        });

        //This button plays seleted music right now
        Button button_play_now = (Button)findViewById(R.id.button_play_now);
        button_play_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, NowPlayActivity.class);
                startActivity(intent);
            }
        });

    }
}
