package com.zangfengshun.musicapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DiscoverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);

        Button button_add_to_list_dis = (Button)findViewById(R.id.button_add_to_list_dis);
        button_add_to_list_dis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiscoverActivity.this, MyListActivity.class);
                startActivity(intent);
            }
        });

        //This button plays seleted music right now
        Button button_play_now_dis = (Button)findViewById(R.id.button_play_now_dis);
        button_play_now_dis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiscoverActivity.this, NowPlayActivity.class);
                startActivity(intent);
            }
        });
    }
}
