package com.zsabo.effects.Models;

import android.media.MediaPlayer;

public class AudioFile {

    private MediaPlayer mPlayer;
    private String title;

    public AudioFile(String title, MediaPlayer mPlayer) {
        this.title = title;
        this.mPlayer = mPlayer;
    }

    public String getTitle() {
        return title;
    }

    public boolean play() {
        if (mPlayer != null && !mPlayer.isPlaying()) {
            mPlayer.start();
            return true;
        }
        return false;
    }
}
