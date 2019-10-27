package com.zsabo.effects.Utilities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class UserSettingsManager {

    private SharedPreferences preference;
    private SharedPreferences.Editor editor;
    private static final String FADE_IN_BUTTON_KEY = "FadeInKey";

    private static final UserSettingsManager ourInstance = new UserSettingsManager();

    public static UserSettingsManager getInstance() {
        return ourInstance;
    }

    private UserSettingsManager() {
    }

    @SuppressLint("CommitPrefEdits")
    public void initManager(Context context) {
        preference = context.getSharedPreferences(context.getApplicationContext().getPackageName(), 0);
        editor = preference.edit();
    }

    public void writeFadeingButtonState(Boolean value) {
        writeBooleanData(value, FADE_IN_BUTTON_KEY);
    }

    public boolean readFadeingButtonState() {
        return readBooleanData(FADE_IN_BUTTON_KEY);
    }

    private void writeBooleanData(boolean value, String key) {
        editor.putBoolean(key, value).commit();
    }

    private boolean readBooleanData(String key) {
        return preference.getBoolean(key, false);
    }

}