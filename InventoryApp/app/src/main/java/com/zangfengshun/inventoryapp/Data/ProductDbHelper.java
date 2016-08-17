package com.zangfengshun.inventoryapp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

/**
 * Created by Zang on 2016-08-04.
 */
public class ProductDbHelper extends SQLiteOpenHelper {
    private static final String LOG_TAG = ProductDbHelper.class.getSimpleName();
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "InventoryApp.db";
    private static Context mContext;

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String COMMA_SEP = ", ";
    private static final String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
            ProductContract.ProductEntry.TABLE_NAME + " (" +
            ProductContract.ProductEntry._ID + " INTEGER PRIMARY KEY, " +
            ProductContract.ProductEntry.COLUMN_NAME_PRODUCT_NAME + TEXT_TYPE + COMMA_SEP +
            ProductContract.ProductEntry.COLUMN_NAME_PRODUCT_PRICE + REAL_TYPE + COMMA_SEP +
            ProductContract.ProductEntry.COLUMN_NAME_PRODUCT_CURRENT_QUANTITY + INTEGER_TYPE + COMMA_SEP +
            ProductContract.ProductEntry.COLUMN_NAME_PRODUCT_SALE_QUANTITY + INTEGER_TYPE + COMMA_SEP +
            ProductContract.ProductEntry.COLUMN_NAME_PRODUCT_IMAGE_LINK + TEXT_TYPE + COMMA_SEP +
            ProductContract.ProductEntry.COLUMN_NAME_SUPPLIER_EMAIL + TEXT_TYPE + " );";
    private static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + ProductContract.ProductEntry.TABLE_NAME + ";";

    public ProductDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(SQL_DELETE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    //This method is used to add product entry into the database.
    public void addProductEntry(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProductContract.ProductEntry.COLUMN_NAME_PRODUCT_NAME, product.getName());
        values.put(ProductContract.ProductEntry.COLUMN_NAME_PRODUCT_PRICE, product.getPrice());
        values.put(ProductContract.ProductEntry.COLUMN_NAME_PRODUCT_CURRENT_QUANTITY, product.getCurrentQuantity());
        values.put(ProductContract.ProductEntry.COLUMN_NAME_PRODUCT_SALE_QUANTITY, product.getSaleQuantity());
        values.put(ProductContract.ProductEntry.COLUMN_NAME_PRODUCT_IMAGE_LINK, product.getImageLink());
        values.put(ProductContract.ProductEntry.COLUMN_NAME_SUPPLIER_EMAIL, product.getSupplierEmail());

        //insert item into database.
        db.insert(ProductContract.ProductEntry.TABLE_NAME, null, values);
        db.close();
    }

    //This method is used to query one product item by id
    public Product querySingleEntry(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {ProductContract.ProductEntry.COLUMN_NAME_PRODUCT_NAME,
                ProductContract.ProductEntry.COLUMN_NAME_PRODUCT_PRICE,
                ProductContract.ProductEntry.COLUMN_NAME_PRODUCT_CURRENT_QUANTITY,
                ProductContract.ProductEntry.COLUMN_NAME_PRODUCT_SALE_QUANTITY,
                ProductContract.ProductEntry.COLUMN_NAME_PRODUCT_IMAGE_LINK,
                ProductContract.ProductEntry.COLUMN_NAME_SUPPLIER_EMAIL};
        String selection = ProductContract.ProductEntry._ID + "=?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursor = db.query(ProductContract.ProductEntry.TABLE_NAME, columns, selection, selectionArgs,
                null, null, null, null);
        Product product = null;
        if (cursor != null) {
            cursor.moveToFirst();
            product = new Product(cursor.getString(1), cursor.getDouble(2), cursor.getInt(3),
                    cursor.getInt(4), cursor.getString(5), cursor.getString(6));
            cursor.close();
        }
        db.close();
        return product;
    }

    //This method is used to query all the products from table.
    public ArrayList<Product> queryAllEntries() {
        ArrayList<Product> productsList = new ArrayList<>();
        final String SQL_QUERY_ALL_ITEMS = "SELECT * FROM " + ProductContract.ProductEntry.TABLE_NAME + ";";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL_QUERY_ALL_ITEMS, null);

        if (cursor.moveToFirst()) {
            do {
                //Log.v(LOG_TAG, cursor.getString(1));
//                Log.v(LOG_TAG, String.valueOf(cursor.getDouble(2)));
//                Log.v(LOG_TAG, String.valueOf(cursor.getInt(3)));
//                Log.v(LOG_TAG, String.valueOf(cursor.getInt(4)));
//                Log.v(LOG_TAG, cursor.getString(5));
//                Log.v(LOG_TAG, cursor.getString(6));
                Product product = new Product(cursor.getString(1), cursor.getDouble(2), cursor.getInt(3),
                        cursor.getInt(4), cursor.getString(5), cursor.getString(6));
                productsList.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return productsList;
    }

    //This method is used to modify the product quantity based on sold number.
    public void updateProductQuantityAfterSelling(Product product, int soldQuantity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProductContract.ProductEntry.COLUMN_NAME_PRODUCT_CURRENT_QUANTITY, product.getCurrentQuantity() - soldQuantity);
        values.put(ProductContract.ProductEntry.COLUMN_NAME_PRODUCT_SALE_QUANTITY, product.getSaleQuantity() + soldQuantity);

        db.update(ProductContract.ProductEntry.TABLE_NAME, values, ProductContract.ProductEntry.COLUMN_NAME_PRODUCT_NAME + " =?",
                new String[]{String.valueOf(product.getName())});
        db.close();
    }

    //This method is used to modify the product quantity based on shipment number.
    public void updateProductQuantityAfterReceiving(Product product, int receivingQuantity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProductContract.ProductEntry.COLUMN_NAME_PRODUCT_CURRENT_QUANTITY, product.getCurrentQuantity() + receivingQuantity);

        db.update(ProductContract.ProductEntry.TABLE_NAME, values, ProductContract.ProductEntry.COLUMN_NAME_PRODUCT_NAME + " =?",
                new String[]{String.valueOf(product.getName())});
        db.close();
    }

    //This method is used to delete a single entry of the specific product.
    public void deleteProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ProductContract.ProductEntry.TABLE_NAME, ProductContract.ProductEntry.COLUMN_NAME_PRODUCT_NAME + " =?",
                new String[]{String.valueOf(product.getName())});
        db.close();
    }

    //This method is used to delete the whole table.
    public void deleteDB() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + ProductContract.ProductEntry.TABLE_NAME + ";");
        db.close();
    }

    //This method is used to get count of products in the database.
    public int getProductCount() {
        String countQuery = "SELECT * FROM " + ProductContract.ProductEntry.TABLE_NAME + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();
        db.close();

        return count;
    }
}
