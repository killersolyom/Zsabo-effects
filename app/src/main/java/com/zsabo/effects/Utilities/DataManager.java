package com.zsabo.effects.Utilities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class DataManager {

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
        editor = preference.edit();
    }

    private void writeLongData(long number, String key) {
        editor.putLong(key, number).commit();
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
}