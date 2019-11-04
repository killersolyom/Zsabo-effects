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
    private int resourceID;
    private MediaPlayer.OnCompletionListener onCompletionListener;

    public AudioFile(String title, int resourceID, Context context) {
        this.title = title;
        this.context = context;
        this.resourceID = resourceID;
        initPlayer();
        onCompletionListener = mediaPlayer -> releaseMediaPlayer();
        audioManager = (AudioManager) context.getSystemService(AUDIO_SERVICE);

    }

    private void initPlayer() {
        mPlayer = MediaPlayer.create(context, resourceID);
        mPlayer.setOnCompletionListener(onCompletionListener);
    }

    private void releaseMediaPlayer() {
        mPlayer.release();
        mPlayer = null;
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
        }
        return false;
    }

    private boolean isMediaSoundDisabled() {
        return (audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) == 0);
    }
}