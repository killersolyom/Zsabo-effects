package com.zsabo.effects.Models;

public class AudioFile {

    private int audioFileResourceID;
    private String title;

    public AudioFile(int audioFileId, String title) {
        this.audioFileResourceID = audioFileId;
        this.title = title;
    }

    public int getAudioResourceId() {
        return audioFileResourceID;
    }

    public String getTitle() {
        return title;
    }
}
