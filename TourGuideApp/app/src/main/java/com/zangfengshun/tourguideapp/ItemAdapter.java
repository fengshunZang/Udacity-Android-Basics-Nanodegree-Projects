package com.zangfengshun.tourguideapp;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Zang on 2016-07-11.
 */
public class ItemAdapter extends ArrayAdapter<Item> {
    private int mColorResourceId;

    public ItemAdapter(Context context, List<Item> objects, int mColorResourceId) {
        super(context, 0, objects);
        this.mColorResourceId = mColorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;


        //check if list item view has already be generated.
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent
            , false);
        }

        Item currentItem = getItem(position);

        //set the them color for the list item
        View textContainer = (View)listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(),mColorResourceId);
        textContainer.setBackgroundColor(color);

        //set corresponding textview and imageview for every list item
        TextView nameTextView = (TextView)listItemView.findViewById(R.id.tour_name_text_view);
        nameTextView.setText(currentItem.getmName());

        TextView informationTextView = (TextView)listItemView.findViewById(R.id.tour_information_text_view);
        informationTextView.setText(currentItem.getmContactInfomation());

        ImageView imageView = (ImageView)listItemView.findViewById(R.id.tour_image_view);
        if (currentItem.hasImage()) {
            imageView.setImageResource(currentItem.getmImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }

        return listItemView;
    }
}

