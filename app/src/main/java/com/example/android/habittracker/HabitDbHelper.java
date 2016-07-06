package com.example.android.habittracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HabitDbHelper extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + HabitContract.HabitEntry.TABLE_NAME + " (" +
                    HabitContract.HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    HabitContract.HabitEntry.COLUMN_NAME_HABIT_NAME + TEXT_TYPE + COMMA_SEP +
                    HabitContract.HabitEntry.COLUMN_NAME_DAY_STREAK + INTEGER_TYPE + COMMA_SEP +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + HabitContract.HabitEntry.TABLE_NAME;

    public HabitDbHelper(Context context) {
        super(context, HabitContract.DATABASE_NAME, null, HabitContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void addHabit(Habit habit) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.COLUMN_NAME_HABIT_NAME, habit.getName());
        values.put(HabitContract.HabitEntry.COLUMN_NAME_DAY_STREAK, habit.getDayStreak());

        db.insert(HabitContract.HabitEntry.TABLE_NAME,
                null,
                values);

        db.close();
    }

    public Cursor getHabit(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                HabitContract.HabitEntry._ID,
                HabitContract.HabitEntry.COLUMN_NAME_HABIT_NAME,
                HabitContract.HabitEntry.COLUMN_NAME_DAY_STREAK,
        };

        Cursor cursor =
                db.query(HabitContract.HabitEntry.TABLE_NAME,
                        projection,
                        " id = ?",
                        new String[]{String.valueOf(id)},
                        null,
                        null,
                        null,
                        null);

        return cursor;
    }

    public int updateHabit(Habit habit) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("habitName", habit.getName());
        values.put("dayStreak", habit.getDayStreak());

        int i = db.update(HabitContract.HabitEntry.TABLE_NAME,
                values,
                HabitContract.HabitEntry._ID + " = ?",
                new String[]{String.valueOf(habit.getId())});

        db.close();

        return i;
    }

    public void deleteHabit(Habit habit) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(HabitContract.HabitEntry.TABLE_NAME,
                HabitContract.HabitEntry._ID + " = ?",
                new String[]{String.valueOf(habit.getId())});

        db.close();
    }
}