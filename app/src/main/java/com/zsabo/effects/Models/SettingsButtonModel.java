package com.zsabo.effects.Models;

public class SettingsButtonModel {
    private Runnable runnable;
    private String title;

    public SettingsButtonModel(Runnable runnable, String title) {
        this.runnable = runnable;
        this.title = title;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public String getTitle() {
        return title;
    }
}
