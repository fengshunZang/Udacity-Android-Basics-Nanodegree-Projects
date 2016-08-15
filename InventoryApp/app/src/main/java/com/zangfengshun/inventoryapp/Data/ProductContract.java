package com.zangfengshun.inventoryapp.Data;

import android.provider.BaseColumns;

/**
 * Created by Zang on 2016-08-04.
 * This is the contract for product table.
 */
public class ProductContract  {
    //Empty constructor
    public ProductContract() {}

    /*
    Inner class defines the table content.
     */
    public static abstract class ProductEntry implements BaseColumns {
        public static final String TABLE_NAME = "product_inventory";
        public static final String COLUMN_NAME_PRODUCT_NAME = "name";
        public static final String COLUMN_NAME_PRODUCT_PRICE = "price";
        public static final String COLUMN_NAME_PRODUCT_CURRENT_QUANTITY = "current_quantity";
        public static final String COLUMN_NAME_PRODUCT_SALE_QUANTITY = "sale_quantity";
        public static final String COLUMN_NAME_PRODUCT_IMAGE_LINK = "image_link";
        public static final String COLUMN_NAME_SUPPLIER_EMAIL = "email";
    }
}
