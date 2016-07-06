package com.zangfengshun.musicapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);

        Button button_play_1 = (Button)findViewById(R.id.button_play_1);
        Button button_play_2 = (Button)findViewById(R.id.button_play_2);
        Button button_play_3 = (Button)findViewById(R.id.button_play_3);
        Button button_play_4 = (Button)findViewById(R.id.button_play_4);
        Button button_play_5 = (Button)findViewById(R.id.button_play_5);

        //The actual intent would be sent to the same activity but with different attributes in order to specify which music should be played.
        button_play_1.setOnClickListener(new PlayOnclickListener());
        button_play_2.setOnClickListener(new PlayOnclickListener());
        button_play_3.setOnClickListener(new PlayOnclickListener());
        button_play_4.setOnClickListener(new PlayOnclickListener());
        button_play_5.setOnClickListener(new PlayOnclickListener());


    }

    private class PlayOnclickListener implements View.OnClickListener {
        public void onClick(View v) {
            Intent intent = new Intent(MyListActivity.this, NowPlayActivity.class);
            startActivity(intent);
        }
    }
}
