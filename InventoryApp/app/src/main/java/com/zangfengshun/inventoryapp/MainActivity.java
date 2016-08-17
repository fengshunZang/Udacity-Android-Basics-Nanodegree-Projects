package com.zangfengshun.inventoryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.zangfengshun.inventoryapp.Data.Product;
import com.zangfengshun.inventoryapp.Data.ProductDbHelper;
import com.zangfengshun.inventoryapp.Data.ProductListAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ProductDbHelper mDbHelper = new ProductDbHelper(this);
    private TextView mDisplayMsg;
    private ListView mListView;
    private ArrayList<Product> mProductsList = new ArrayList<>();
    ProductListAdapter mAdapter;
    private static final String LOG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set adapter to the listView;
        mListView = (ListView) findViewById(R.id.list_view);
        mAdapter = new ProductListAdapter(MainActivity.this, mProductsList);
        mListView.setAdapter(mAdapter);

        mDisplayMsg = (TextView) findViewById(R.id.display_message);

        if (mDbHelper.getProductCount() == 0) {
            mDisplayMsg.setVisibility(View.VISIBLE);
            mListView.setVisibility(View.GONE);
        } else {
            mListView.setVisibility(View.VISIBLE);
            mDisplayMsg.setVisibility(View.GONE);
        }

        mProductsList.addAll(mDbHelper.queryAllEntries());
        mAdapter.notifyDataSetChanged();

        //Handle list item click event.
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Product item = mAdapter.getItem(position);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("item_info", item);
                Log.v(LOG_TAG, "Intent already sent");
                startActivityForResult(intent, 0);
            }
        });

        //Handle the add button click event.
        Button addProduct = (Button) findViewById(R.id.add_product_button);
        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddProductActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mListView.getVisibility() == View.GONE) {
            mListView.setVisibility(View.VISIBLE);
            mDisplayMsg.setVisibility(View.GONE);
        }

        mAdapter.clear();
        mProductsList.clear();
        mProductsList.addAll(mDbHelper.queryAllEntries());
        Log.v(LOG_TAG, "returning from add product activity.");

        for (Product p : mProductsList) {
            Log.v(LOG_TAG, p.getName());
        }

        mAdapter.notifyDataSetChanged();

        if (mProductsList != null) {
            if (mProductsList.size() == 0) {
                mListView.setVisibility(View.GONE);
            } else {
                mListView.setVisibility(View.VISIBLE);
                mDisplayMsg.setVisibility(View.GONE);
            }
        } else {
            mListView.setVisibility(View.GONE);
        }
    }
}
