package com.zangfengshun.inventoryapp.Data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Zang on 2016-08-04.
 */
public class Product implements Parcelable {
    private String mName;
    private double mPrice;
    private int mCurrentQuantity;
    private int mSaleQuantity;
    private String mImageLink;
    private String mSupplierEmail;

    public Product(String name, double price, int currentQuantity, int saleQuantity, String imageLink,
                   String supplierEmail) {
        mName = name;
        mPrice = price;
        mCurrentQuantity = currentQuantity;
        mSaleQuantity = saleQuantity;
        mImageLink = imageLink;
        mSupplierEmail = supplierEmail;
    }

    public String getName() {
        return mName;
    }

    public String getSupplierEmail() {
        return mSupplierEmail;
    }

    public String getImageLink() {
        return mImageLink;
    }

    public int getCurrentQuantity() {
        return mCurrentQuantity;
    }

    public int getSaleQuantity() {
        return mSaleQuantity;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public void setPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public void setCurrentQuantity(int mCurrentQuantity) {
        this.mCurrentQuantity = mCurrentQuantity;
    }

    public void setSaleQuantity(int mSaleQuantity) {
        this.mSaleQuantity = mSaleQuantity;
    }

    public void setImageLink(String mImageLink) {
        this.mImageLink = mImageLink;
    }

    public void setSupplierEmail(String mSupplierEmail) {
        this.mSupplierEmail = mSupplierEmail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            Product product = new Product(source.readString(), source.readDouble(),
                    source.readInt(), source.readInt(), source.readString(), source.readString());
            return product;
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(mName);
        parcel.writeDouble(mPrice);
        parcel.writeInt(mCurrentQuantity);
        parcel.writeInt(mSaleQuantity);
        parcel.writeString(mImageLink);
        parcel.writeString(mSupplierEmail);
    }
}
