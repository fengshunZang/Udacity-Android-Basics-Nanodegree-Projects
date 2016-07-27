package com.zangfengshun.tourguideapp;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attaction_list);

        ArrayList<Item> items = new ArrayList<>();
        Resources resources = getResources();
        items.add(new Item(resources.getString(R.string.event1_name), resources.getString(R.string.event1_des), R.drawable.luzia));
        items.add(new Item(resources.getString(R.string.event2_name), resources.getString(R.string.event2_des), R.drawable.piknic_electronik));
        items.add(new Item(resources.getString(R.string.event3_name), resources.getString(R.string.event3_des), R.drawable.festival_de_jazz));
        items.add(new Item(resources.getString(R.string.event4_name), resources.getString(R.string.event4_des), R.drawable.festival_de_percussion));
        items.add(new Item(resources.getString(R.string.event5_name), resources.getString(R.string.event5_des), R.drawable.feux_loto));
        items.add(new Item(resources.getString(R.string.event6_name), resources.getString(R.string.event6_des), R.drawable.arts_du_cirque_circus));

        ItemAdapter adapter = new ItemAdapter(this, items, R.color.event);
        ListView listView = (ListView) findViewById(R.id.list);
        if(listView !=null) {
            listView.setAdapter(adapter);
        }
    }
}
