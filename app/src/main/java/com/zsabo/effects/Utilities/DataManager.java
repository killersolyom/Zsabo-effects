package com.zsabo.effects.Utilities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class DataManager {
    private String ALPHA_KEY = "Alpha_key";
    private SharedPreferences preference;
    private SharedPreferences.Editor editor;

    private static final DataManager ourInstance = new DataManager();

    public static DataManager getInstance() {
        return ourInstance;
    }

    private DataManager() {
    }

    @SuppressLint("CommitPrefEdits")
    public void initManager(Context context) {
        preference = context.getSharedPreferences(context.getApplicationContext().getPackageName(), 0);
    }

    private void writeLongData(long number, String key) {
        preference.edit().putLong(key, number).apply();
    }

    private long readLongData(String key) {
        return preference.getLong(key, 0);
    }

    public void increaseListenCounter(String title) {
        writeLongData(readLongData(title) + 1, title);
    }

    public long getListenCounter(String title) {
        return readLongData(title);
    }

    public void resetCounter(String title) {
        preference.edit().remove(title).apply();
    }

    public void setAlphaValue(int value) {
        writeIntData(value, ALPHA_KEY);
    }

    public int getAlphaValue() {
        int value = readIntData(ALPHA_KEY);
        return value == 0 ? 15 : value;
    }

    private void writeIntData(int value, String key) {
        preference.edit().putInt(key, value).apply();
    }

    private int readIntData(String key) {
        return preference.getInt(key, 0);
    }
}