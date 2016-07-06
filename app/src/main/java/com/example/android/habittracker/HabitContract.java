package com.example.android.habittracker;

import android.provider.BaseColumns;

/**
 * Created by IrvinShandy on 7/2/16.
 */
public class HabitContract {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "HabitDB";

    public HabitContract() {
    }

    public static abstract class HabitEntry implements BaseColumns {
        public static final String TABLE_NAME = "habit";
        public static final String COLUMN_NAME_HABIT_NAME = "habitName";
        public static final String COLUMN_NAME_DAY_STREAK = "dayStreak";
    }
}