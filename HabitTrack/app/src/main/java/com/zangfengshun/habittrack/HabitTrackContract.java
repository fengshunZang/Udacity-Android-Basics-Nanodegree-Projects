package com.zangfengshun.habittrack;

import android.provider.BaseColumns;

/**
 * Created by Zang on 2016-07-18.
 */
public final class HabitTrackContract {
    //empty constructor
    public HabitTrackContract() {};

    /* Inner class that defines the table contents.*/
    public static abstract class ActivityEntry implements BaseColumns {
        public static final String TABLE_NAME = "habit track";
        public static final String COLUMN_NAME_ACTIVITY_NAME = "activity";
        public static final String COLUMN_NAME_START_TIME = "starting time";
        public static final String COLUMN_NAME_END_TIME = "ending time";
        //Priority of activity is rated between 1 to 5;
        public static final String COLUMN_NAME_PRIORITY = "priority";
        public static final String COLUMN_NAME_REMARK = "remark";
    }
}
