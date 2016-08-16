package com.zangfengshun.inventoryapp;

import android.app.Application;
import com.facebook.stetho.Stetho;

/**
 * Created by Zang on 2016-08-15.
 */
public class MyApplication  extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
