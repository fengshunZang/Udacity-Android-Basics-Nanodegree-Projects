package com.zangfengshun.booklistingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zang on 2016-07-12.
 */
public class ItemAdapter extends ArrayAdapter<Item> {
    public ItemAdapter(Context context, ArrayList<Item> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Item currentItem = getItem(position);

        TextView titleTextView = (TextView)listItemView.findViewById(R.id.title);
        titleTextView.setText(currentItem.getTitle());

        TextView authorTextView = (TextView)listItemView.findViewById(R.id.author);
        authorTextView.setText(currentItem.getAuthor());

        return listItemView;
    }
}
