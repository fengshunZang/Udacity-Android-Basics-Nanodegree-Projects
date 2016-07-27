package com.zangfengshun.tourguideapp;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class RestaurantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attaction_list);

        ArrayList<Item> items = new ArrayList<>();
        Resources resources = getResources();
        items.add(new Item(resources.getString(R.string.restaurant1_name), resources.getString(R.string.restaurant1_tel), R.drawable.ganache));
        items.add(new Item(resources.getString(R.string.restaurant2_name), resources.getString(R.string.restaurant2_tel), R.drawable.damas_restaurant));
        items.add(new Item(resources.getString(R.string.restaurant3_name), resources.getString(R.string.restaurant3_tel), R.drawable.veal));
        items.add(new Item(resources.getString(R.string.restaurant4_name), resources.getString(R.string.restaurant4_tel), R.drawable.bouillon_bilk));
        items.add(new Item(resources.getString(R.string.restaurant5_name), resources.getString(R.string.restaurant5_tel), R.drawable.europea));
        items.add(new Item(resources.getString(R.string.restaurant6_name), resources.getString(R.string.restaurant6_tel), R.drawable.robin_square));
        items.add(new Item(resources.getString(R.string.restaurant7_name), resources.getString(R.string.restaurant7_tel), R.drawable.cafe_regine));

        ItemAdapter adapter = new ItemAdapter(this, items, R.color.restaurant);
        ListView listView = (ListView) findViewById(R.id.list);
        if (listView != null) {
            listView.setAdapter(adapter);
        }
    }
}
