package com.zangfengshun.inventoryapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zangfengshun.inventoryapp.Data.Product;
import com.zangfengshun.inventoryapp.Data.ProductDbHelper;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;

public class DetailActivity extends AppCompatActivity {
    private static final String LOG_TAG = DetailActivity.class.getSimpleName();
    private ProductDbHelper mDbHelper = new ProductDbHelper(this);;
    private Product mProduct;

    private TextView nameDetail;
    private TextView priceDetail;
    private TextView currentQuantityDetail;
    private TextView saleQuantityDetail;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        Log.v(LOG_TAG, "Received intent");

        mProduct = intent.getParcelableExtra("item_info");

        nameDetail = (TextView)findViewById(R.id.name_detail);
        priceDetail = (TextView)findViewById(R.id.price_detail);
        currentQuantityDetail = (TextView)findViewById(R.id.current_quantity_detail);
        saleQuantityDetail = (TextView)findViewById(R.id.sale_quantity_detail);
        imageView = (ImageView)findViewById(R.id.imageView_product);

        display(mProduct);

        //Handle the substrate button click event.
        Button buttonSubtract = (Button)findViewById(R.id.button_subtract);
        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDbHelper.updateProductQuantityAfterSelling(mProduct , 1);
                mProduct.setSaleQuantity(mProduct.getSaleQuantity() + 1);
                mProduct.setCurrentQuantity(mProduct.getCurrentQuantity() - 1);
                display(mProduct);
            }
        });

        //Handle the add button click event.
        Button buttonAdd = (Button)findViewById(R.id.button_add);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDbHelper.updateProductQuantityAfterReceiving(mProduct , 1);
                mProduct.setCurrentQuantity(mProduct.getCurrentQuantity() + 1);
                display(mProduct);
            }
        });

        //Handle the order button click event.
        Button buttonOrder = (Button)findViewById(R.id.button_order);
        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + mProduct.getSupplierEmail()));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "PRODUCTS ORDER");
                startActivity(emailIntent);
            }
        });

        //Handle the delete button click event.
        Button buttonDelete = (Button)findViewById(R.id.button_delete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDbHelper.deleteProduct(mProduct);
                setResult(RESULT_OK, new Intent(DetailActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void display(Product product) {
        nameDetail.setText(product.getName());

        priceDetail.setText(String.valueOf(product.getPrice()));

        currentQuantityDetail.setText(String.valueOf(product.getCurrentQuantity()));

        saleQuantityDetail.setText(String.valueOf(product.getSaleQuantity()));

       // imageView.setImageResource(R.mipmap.ic_launcher);
        Bitmap selectedImage = getBitmapFromUri(Uri.parse(product.getImageLink()));
        Log.v(LOG_TAG, product.getImageLink());
        if (selectedImage != null) {
            imageView.setImageBitmap(selectedImage);
        }
        
//        try {
//            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriFromPath);
//            // Log.d(TAG, String.valueOf(bitmap));
//
//            // ImageView imageView = (ImageView) findViewById(R.id.imageView);
//            imageView.setImageBitmap(bitmap);
//        } catch (IOException e) {
//            Log.v(LOG_TAG, e.toString());
//        }

    }

    private Bitmap getBitmapFromUri(Uri uri) {
        ParcelFileDescriptor parcelFileDescriptor = null;
        try {
            parcelFileDescriptor = this.getContentResolver().openFileDescriptor(uri, "r");
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
            parcelFileDescriptor.close();
            return image;
        } catch (Exception e) {
            Log.v(LOG_TAG, "Failed to load image.", e);
            return null;
        } finally {
            try {
                if (parcelFileDescriptor != null) {
                    parcelFileDescriptor.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.v(LOG_TAG, "Error closing ParcelFile Descriptor");
            }
        }
    }
}
