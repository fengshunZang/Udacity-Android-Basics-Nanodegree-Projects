package com.zangfengshun.habittrack;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityReaderDbHelper mDbHelper = new ActivityReaderDbHelper(this);
        //Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        insertEntry(db, 1, "Getting up", "2016-07-18 07:00:00.000", "2016-07-18 07:15:00.000", 2, "Including dressing up");
        insertEntry(db, 2, "Making breakfast", "2016-07-18 07:20:00.000", "2016-07-18 07:25:00.000", 2, "Breakfast includes egg and milk");
        insertEntry(db, 3, "Working", "2016-07-18 09:00:00.000", "2016-07-18 11:30:00.000", 5, "Organizing related files");
        insertEntry(db, 4, "Having Lunch", "2016-07-18 12:00:00.000", "2016-07-18 12:30:00.000", 3, "Burger and fries");
        insertEntry(db, 5, "Working", "2016-07-18 13:00:00.000", "2016-07-18 17:30:00.000", 5, "Organizing remaining related files");

    }

    //This insert method returns the id of new row.
    public long insertEntry(SQLiteDatabase db, int id, String activityName, String startTime, String endTime, int priority, String remark) {
        ContentValues values = new ContentValues();
        values.put(HabitTrackContract.ActivityEntry._ID, id);
        values.put(HabitTrackContract.ActivityEntry.COLUMN_NAME_ACTIVITY_NAME, activityName);
        values.put(HabitTrackContract.ActivityEntry.COLUMN_NAME_START_TIME, startTime);
        values.put(HabitTrackContract.ActivityEntry.COLUMN_NAME_END_TIME, endTime);
        values.put(HabitTrackContract.ActivityEntry.COLUMN_NAME_PRIORITY, priority);
        values.put(HabitTrackContract.ActivityEntry.COLUMN_NAME_REMARK, remark);
        return db.insert(HabitTrackContract.ActivityEntry.TABLE_NAME, null, values);
    }

    //This query method returns a cursor object.
    public Cursor queryEntry(SQLiteDatabase db, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        String[] projection = {HabitTrackContract.ActivityEntry._ID, HabitTrackContract.ActivityEntry.COLUMN_NAME_ACTIVITY_NAME,
                HabitTrackContract.ActivityEntry.COLUMN_NAME_START_TIME, HabitTrackContract.ActivityEntry.COLUMN_NAME_END_TIME,
                HabitTrackContract.ActivityEntry.COLUMN_NAME_PRIORITY, HabitTrackContract.ActivityEntry.COLUMN_NAME_REMARK};
        Cursor c = db.query(HabitTrackContract.ActivityEntry.TABLE_NAME, projection, selection, selectionArgs, groupBy, having, orderBy);
        c.moveToFirst();
        return c;
    }

    //Here is a single delete method that deletes all the entries from the table.
    public void deleteAllEntries(SQLiteDatabase db) {
        db.execSQL(ActivityReaderDbHelper.SQL_DELETE_ENTRIES);
    }

    //Here is a single update method that updates as least one value in one column in the table.
    public void updateEntry(SQLiteDatabase db, ContentValues newValues, String selection, String[] selectionArgs) {
        db.update(HabitTrackContract.ActivityEntry.TABLE_NAME, newValues, selection, selectionArgs);
    }
}
