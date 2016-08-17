package com.zangfengshun.inventoryapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
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

                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
                Log.v("AddProductActivity", "get image");
                // Then define onActivityResult to do something with picked image
            }
        });

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBlank(name)) {
                    Toast toast = Toast.makeText(AddProductActivity.this, "Please add the name of the product.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                } else {
                    mName = name.getText().toString();
                }

                if (checkBlank(price) && !checkIsFloat(price)) {
                    Toast toast = Toast.makeText(AddProductActivity.this, "Please add the unit price of the product.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                } else {
                    mPrice = Double.valueOf(price.getText().toString());
                }

                //The initial figure of current/sale quantity could be zero, so the notice toast is not required.
                if (checkBlank(currentQuantity) && !checkIsInteger(currentQuantity)) {
                    Toast toast = Toast.makeText(AddProductActivity.this, "Please add the current quantity of the product.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                } else {
                    mCurrentQuantity = Integer.valueOf(currentQuantity.getText().toString());
                }

                if (checkBlank(saleQuantity) && !checkIsInteger(saleQuantity)) {
                    Toast toast = Toast.makeText(AddProductActivity.this, "Please add the sold quantity of the product.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                } else {
                    mSaleQuantity = Integer.valueOf(saleQuantity.getText().toString());
                }

                if (checkBlank(supplierEmail)) {
                    Toast toast = Toast.makeText(AddProductActivity.this, "Please add an supplier's email address of the product.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                } else {
                    mEmail = supplierEmail.getText().toString();
                }

                if (mImageLink == null) {
                    Toast toast = Toast.makeText(AddProductActivity.this, "Please select a picture of the product from gallery.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                } else {
                    mProduct = new Product(mName, mPrice, mCurrentQuantity, mSaleQuantity, mImageLink, mEmail);
                    mDbHelper.addProductEntry(mProduct);
                }

                setResult(RESULT_OK, new Intent());
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int reqCode, int resCode, Intent data) {

        Log.v(LOG_TAG, "received image data");

        if (resCode == RESULT_OK && reqCode == PICK_IMAGE_REQUEST && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            Log.v(LOG_TAG, "received image uri");

            //The uri information is stored in every product item.
            mImageLink = selectedImageUri.toString();
        }
        super.onActivityResult(reqCode, resCode, data);
    }

    public boolean checkBlank(EditText words) {
        return words.getText().toString().trim().length() == 0;
    }

    public boolean checkIsFloat(EditText words) {
        try {
            Float.parseFloat(words.getText().toString());
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean checkIsInteger(EditText words) {
        try {
            Integer.parseInt(words.getText().toString());
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
