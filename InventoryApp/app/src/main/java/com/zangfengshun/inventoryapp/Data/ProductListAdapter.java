package com.zangfengshun.inventoryapp.Data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.zangfengshun.inventoryapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zang on 2016-08-08.
 */
public class ProductListAdapter extends ArrayAdapter<Product>{
    public ProductListAdapter(Context context, ArrayList<Product> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listView = convertView;

        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Product currentProduct = getItem(position);

        TextView name = (TextView)listView.findViewById(R.id.name_list_item);
        name.setText(currentProduct.getName());

        TextView price = (TextView)listView.findViewById(R.id.price_list_item);
        price.setText(String.valueOf(currentProduct.getPrice()));

        TextView currentQuantity = (TextView)listView.findViewById(R.id.current_quantity_list_item);
        currentQuantity.setText(currentProduct.getCurrentQuantity());

        int saleQ = currentProduct.getSaleQuantity();
        TextView saleQuantity = (TextView)listView.findViewById(R.id.sale_quantity_list_item);
        saleQuantity.setText(saleQ);

        return listView;
    }
}
