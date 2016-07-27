package com.zangfengshun.tourguideapp;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class HotelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attaction_list);

        ArrayList<Item> items = new ArrayList<>();
        Resources resources = getResources();
        items.add(new Item(resources.getString(R.string.hotel1_name), resources.getString(R.string.hotel1_tel)));
        items.add(new Item(resources.getString(R.string.hotel2_name), resources.getString(R.string.hotel2_tel)));
        items.add(new Item(resources.getString(R.string.hotel3_name), resources.getString(R.string.hotel3_tel)));
        items.add(new Item(resources.getString(R.string.hotel4_name), resources.getString(R.string.hotel4_tel)));
        items.add(new Item(resources.getString(R.string.hotel5_name), resources.getString(R.string.hotel5_tel)));
        items.add(new Item(resources.getString(R.string.hotel6_name), resources.getString(R.string.hotel6_tel)));
        items.add(new Item(resources.getString(R.string.hotel7_name), resources.getString(R.string.hotel7_tel)));
        items.add(new Item(resources.getString(R.string.hotel8_name), resources.getString(R.string.hotel8_tel)));

        ItemAdapter adapter = new ItemAdapter(this, items, R.color.hotel);
        ListView listView = (ListView) findViewById(R.id.list);
        if (listView != null) {
            listView.setAdapter(adapter);
        }
    }
}
