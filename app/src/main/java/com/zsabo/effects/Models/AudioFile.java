package com.zsabo.effects.Models;

import android.media.MediaPlayer;

public class AudioFile {

    private MediaPlayer audioFile;
    private String title;

    public AudioFile(MediaPlayer audioFile, String title) {
        this.audioFile = audioFile;
        this.title = title;
    }

    public MediaPlayer getAudioFile() {
        return audioFile;
    }

    public String getTitle() {
        return title;
    }
}
