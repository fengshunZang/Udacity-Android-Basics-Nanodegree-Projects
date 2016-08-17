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
    private ProductDbHelper mDbHelper = new ProductDbHelper(getContext());
    public ProductListAdapter(Context context, ArrayList<Product> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listView = convertView;

        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        final Product currentProduct = getItem(position);

        TextView name = (TextView)listView.findViewById(R.id.name_list_item);
        name.setText(currentProduct.getName());

        TextView price = (TextView)listView.findViewById(R.id.price_list_item);
        price.setText(String.valueOf(currentProduct.getPrice()));

        TextView currentQuantity = (TextView)listView.findViewById(R.id.current_quantity_list_item);
        currentQuantity.setText(String.valueOf(currentProduct.getCurrentQuantity()));

        TextView saleQuantity = (TextView)listView.findViewById(R.id.sale_quantity_list_item);
        saleQuantity.setText(String.valueOf(currentProduct.getSaleQuantity()));

        Button buttonSold = (Button)listView.findViewById(R.id.button_sold);
        buttonSold.setFocusable(false);
        buttonSold.setFocusableInTouchMode(false);
        buttonSold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentProduct.getCurrentQuantity() == 0 ) {
                    return;
                }
                mDbHelper.updateProductQuantityAfterSelling(currentProduct, 1);
                currentProduct.setSaleQuantity(currentProduct.getSaleQuantity() + 1);
                currentProduct.setCurrentQuantity(currentProduct.getCurrentQuantity() - 1);
                notifyDataSetChanged();
            }
        });

        return listView;
    }
}
