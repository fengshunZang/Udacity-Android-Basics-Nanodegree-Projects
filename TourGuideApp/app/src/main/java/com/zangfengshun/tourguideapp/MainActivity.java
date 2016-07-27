package com.zangfengshun.tourguideapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    TextView attraction;
    TextView restaurant;
    TextView hotel;
    TextView event;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Send explicit intent to other activities to fulfill navigation.
        attraction = (TextView)findViewById(R.id.attraction);
        attraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAttraction = new Intent(MainActivity.this, AttractionActivity.class);
                startActivity(intentAttraction);
            }
        });

        restaurant = (TextView)findViewById(R.id.restaurant);
        restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intentRestaurant = new Intent(MainActivity.this, RestaurantActivity.class);
                    startActivity(intentRestaurant);
            }
        });

        hotel = (TextView)findViewById(R.id.hotel);
        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHotel = new Intent(MainActivity.this, HotelActivity.class);
                startActivity(intentHotel);
            }
        });

        event = (TextView)findViewById(R.id.event);
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEvent = new Intent(MainActivity.this, EventActivity.class);
                startActivity(intentEvent);
            }
        });
    }
}
