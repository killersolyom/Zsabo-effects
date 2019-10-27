package com.zsabo.effects.Utilities;

import android.content.Context;
import android.media.MediaPlayer;

import com.zsabo.effects.Models.AudioFile;

public class AudioPlayerManager {

    private MediaPlayer mPlayer;
    private Context context;

    public AudioPlayerManager(Context context) {
        this.mPlayer = new MediaPlayer();
        this.context = context;
    }

    public void playAudio(AudioFile audioFile) {
        if (UserSettingsManager.getInstance().readFadeingButtonState()) {
            MediaPlayer mp = MediaPlayer.create(context, audioFile.getAudioResourceId());
            mp.start();
        } else {
            mPlayer.stop();
            mPlayer = MediaPlayer.create(context, audioFile.getAudioResourceId());
            mPlayer.start();
        }
    }

}
