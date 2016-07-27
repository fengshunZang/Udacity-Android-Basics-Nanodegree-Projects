package com.zangfengshun.tourguideapp;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class AttractionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attaction_list);

        ArrayList<Item> items = new ArrayList<>();
        Resources resources = getResources();
        items.add(new Item(resources.getString(R.string.attraction1_name), resources.getString(R.string.attraction1_tel), R.drawable.notre_dame_basilica_basilique));
        items.add(new Item(resources.getString(R.string.attraction2_name), resources.getString(R.string.attraction2_tel), R.drawable.hotel_de_ville_city_hall));
        items.add(new Item(resources.getString(R.string.attraction3_name), resources.getString(R.string.attraction3_tel), R.drawable.parc_du_mont_royal));
        items.add(new Item(resources.getString(R.string.attraction4_name), resources.getString(R.string.attraction4_tel), R.drawable.montreal_botanical_gardens));
        items.add(new Item(resources.getString(R.string.attraction5_name), resources.getString(R.string.attraction5_tel), R.drawable.st_joseph_s_oratory_of));
        items.add(new Item(resources.getString(R.string.attraction6_name), resources.getString(R.string.attraction6_tel), R.drawable.musee_des_beaux_arts));
        items.add(new Item(resources.getString(R.string.attraction7_name), resources.getString(R.string.attraction7_web), R.drawable.tour_voir_quebec));

        ItemAdapter adapter = new ItemAdapter(this, items, R.color.attraction);
        ListView listView = (ListView) findViewById(R.id.list);
        if (listView != null) {
            listView.setAdapter(adapter);
        }
    }
}
