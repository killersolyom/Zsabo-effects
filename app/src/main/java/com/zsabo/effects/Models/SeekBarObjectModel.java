package com.zsabo.effects.Models;

import com.zsabo.effects.Communication.SettingsFragmentInterface;

public class SeekBarObjectModel {

    private String title;
    private String messageKey;
    public SettingsFragmentInterface mInterface;

    public SeekBarObjectModel(String title, String key, SettingsFragmentInterface settingsFragmentInterface) {
        this.title = title;
        this.mInterface = settingsFragmentInterface;
        this.messageKey = key;
    }

    public String getTitle() {
        return title;
    }

    public String getMessageKey() {
        return messageKey;
    }
}
