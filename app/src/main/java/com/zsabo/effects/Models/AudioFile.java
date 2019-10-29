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

    public void play() {
        if (mPlayer != null) {
            if (mPlayer.isPlaying()) {
                mPlayer.stop();
            }
            mPlayer.start();
        }
    }
}
