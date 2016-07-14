package com.zangfengshun.booklistingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        ArrayList<Item> result = (ArrayList<Item>) intent.getSerializableExtra("result");

        //Check result to be empty or not and show notice.
        if (result.isEmpty()) {
            Toast toast = Toast.makeText(this, getResources().getText(R.string.No_result_found), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }

        //Setting adapter to listview.
        list = (ListView) findViewById(R.id.list);
        ItemAdapter adapter = new ItemAdapter(this, result);
        list.setAdapter(adapter);
    }
}
