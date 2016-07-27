package com.zangfengshun.habittrack;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Zang on 2016-07-18.
 */
public class ActivityReaderDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "HabitTrack.db";
    private static Context myContext;

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = "INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE" + HabitTrackContract.ActivityEntry.TABLE_NAME + "(" +
            HabitTrackContract.ActivityEntry._ID + TEXT_TYPE + " INTEGER PRIMARY KEY," + HabitTrackContract.ActivityEntry.COLUMN_NAME_ACTIVITY_NAME +
            TEXT_TYPE + COMMA_SEP + HabitTrackContract.ActivityEntry.COLUMN_NAME_START_TIME + TEXT_TYPE + COMMA_SEP + HabitTrackContract.ActivityEntry.COLUMN_NAME_END_TIME
            + TEXT_TYPE + COMMA_SEP + HabitTrackContract.ActivityEntry.COLUMN_NAME_PRIORITY + INTEGER_TYPE + COMMA_SEP + HabitTrackContract.ActivityEntry.COLUMN_NAME_REMARK +
            TEXT_TYPE + ")";
    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + HabitTrackContract.ActivityEntry.TABLE_NAME;

    public ActivityReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }


    public void deleteDatabase(SQLiteDatabase sqLiteDatabase) {
       myContext.deleteDatabase(DATABASE_NAME);
    }
}
