package com.zsabo.effects.Models;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import com.zsabo.effects.R;
import com.zsabo.effects.Utilities.FragmentNavigation;

import static android.content.Context.AUDIO_SERVICE;

public class AudioFile {

    private MediaPlayer mPlayer;
    private AudioManager audioManager;
    private String title;
    private Context context;

    public AudioFile(String title, MediaPlayer mPlayer, Context context) {
        this.title = title;
        this.mPlayer = mPlayer;
        this.context = context;
        audioManager = (AudioManager) context.getSystemService(AUDIO_SERVICE);
    }

    public String getTitle() {
        return title;
    }

    public boolean play() {
        if (mPlayer != null && !mPlayer.isPlaying()) {
            if (!isMediaSoundDisabled()) {
                mPlayer.start();
                return true;
            } else {
                FragmentNavigation.getInstance().showNotificationBar("Óriási hiba!", "Adjál rá hangot vaze!", context.getDrawable(R.drawable.warning_image), true);
            }
            return false;
        }
        return false;
    }

    private boolean isMediaSoundDisabled() {
        return (audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) == 0);
    }
}
