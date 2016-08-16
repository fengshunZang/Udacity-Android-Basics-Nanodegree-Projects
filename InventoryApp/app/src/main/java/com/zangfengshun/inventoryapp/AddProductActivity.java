package com.zangfengshun.inventoryapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zangfengshun.inventoryapp.Data.Product;
import com.zangfengshun.inventoryapp.Data.ProductDbHelper;

public class AddProductActivity extends AppCompatActivity {
    private Product mProduct;
    private ProductDbHelper mDbHelper = new ProductDbHelper(this);
    private String mName;
    private Double mPrice;
    private int mCurrentQuantity;
    private int mSaleQuantity;
    private String mImageLink;
    private String mEmail;
    private int PICK_IMAGE_REQUEST = 1;
    private static final String LOG_TAG = "AddProductActivity";

    private EditText name;
    private EditText price;
    private EditText currentQuantity;
    private EditText saleQuantity;
    private EditText supplierEmail;
    private Button addImage;
    private Button addProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        name = (EditText) findViewById(R.id.name_add_product);
        price = (EditText) findViewById(R.id.price_add_product);
        currentQuantity = (EditText) findViewById(R.id.current_quantity_add_product);
        saleQuantity = (EditText) findViewById(R.id.sale_quantity_add_product);
        supplierEmail = (EditText) findViewById(R.id.supplier_email_add_product);
        addImage = (Button) findViewById(R.id.button_add_image);
        addProduct = (Button) findViewById(R.id.button_addProductIntoDB);

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;

                if (Build.VERSION.SDK_INT < 19) {
                    intent = new Intent(Intent.ACTION_GET_CONTENT);
                } else {
                    intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                }

                //checkWriteToExternalPerms();
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
                Log.v("AddProductActivity", "get image");
                // Then define onActivityResult to do something with picked image
            }
        });

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mName = name.getText().toString();
                mPrice = Double.valueOf(price.getText().toString());
                mCurrentQuantity = Integer.valueOf(currentQuantity.getText().toString());
                mSaleQuantity = Integer.valueOf(saleQuantity.getText().toString());
                mEmail = supplierEmail.getText().toString();
                mProduct = new Product(mName, mPrice, mCurrentQuantity, mSaleQuantity, mImageLink, mEmail);

                Log.v(LOG_TAG, "Prepare to set data to a product object");

//                if (mProduct.getImageLink() == null) {
//                    Toast toast = Toast.makeText(AddProductActivity.this, "Please add image.", Toast.LENGTH_SHORT);
//                    toast.show();
//                    mDbHelper.addProductEntry(mProduct);
//                } else {
//                    mDbHelper.addProductEntry(mProduct);
//                }
                mDbHelper.addProductEntry(mProduct);
                setResult(RESULT_OK, new Intent());
                finish();
            }
        });

    }

    private void checkWriteToExternalPerms() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
            } else {
            }
        } else {
        }
    }

    @Override
    protected void onActivityResult(int reqCode, int resCode, Intent data) {

        Log.v(LOG_TAG, "received image data");

        if (resCode == RESULT_OK && reqCode == PICK_IMAGE_REQUEST && data != null && data.getData() != null) {
            String selectedImagePath = null;
            Uri selectedImageUri = data.getData();
            Log.v(LOG_TAG, "received image uri");

//            String[] projection = {MediaStore.Images.Media.DATA};
//
//            Cursor cursor = getContentResolver().query(selectedImageUri, projection, null, null, null);
//            Log.v(LOG_TAG, "query absolute path from uri");
//
//            if (cursor != null) {
//                int columnIndex = cursor.getColumnIndexOrThrow(projection[0]);
//                cursor.moveToFirst();
//                selectedImagePath = cursor.getString(columnIndex);
//                cursor.close();
//            }
//
//            if (selectedImagePath == null) {
//                Log.v(LOG_TAG, "Path is null");
//            }
            mImageLink = selectedImageUri.toString();
        }
        super.onActivityResult(reqCode, resCode, data);
    }
}
