package com.example.android.habittracker;

/**
 * Created by IrvinShandy on 7/2/16.
 */
public class Habit {
    private int mId;
    private String mHabitName;
    private int mDayStreak;

    public Habit() {
    }

    public Habit(String habitName, int dayStreak) {
        super();
        mHabitName = habitName;
        mDayStreak = dayStreak;
    }

    public String getName() {
        return mHabitName;
    }

    public int getDayStreak() {
        return mDayStreak;
    }

    public int getId() {
        return mId;
    }

    @Override
    public String toString() {
        return "Habit [id=" + mId + ", habitName=" + mHabitName + ", dayStreak=" + mDayStreak
                + "]";
    }
}
