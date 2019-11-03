package com.zsabo.effects.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.zsabo.effects.Models.AudioFile;
import com.zsabo.effects.R;
import com.zsabo.effects.Utilities.DataManager;
import com.zsabo.effects.Utilities.GlideUtils;

public class AudioItemView extends ConstraintLayout {

    private TextView listenCounter;
    private AudioItemTitleView audioTitleView;

    public AudioItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.audio_item_view, this, true);
        ImageView itemImage = findViewById(R.id.item_image_view);
        listenCounter = findViewById(R.id.item_listen_counter);
        audioTitleView = findViewById(R.id.item_title_layout);
        GlideUtils.getInstance().loadImage(R.drawable.play_button_icon, itemImage);
    }

    public void setData(final AudioFile audioFile) {
        audioTitleView.setTitle(audioFile.getTitle());
        setCounterNumber(audioFile);
        addClickListener(audioFile);
    }

    private void addClickListener(AudioFile audioFile) {
        this.setOnClickListener(view -> onItemSelected(audioFile));
    }

    private void onItemSelected(AudioFile audioFile) {
        if (audioFile.play()) {
            DataManager.getInstance().increaseListenCounter(audioFile.getTitle());
            setCounterNumber(audioFile);
        }
    }

    private void setCounterNumber(AudioFile audioFile) {
        long number = DataManager.getInstance().getListenCounter(audioFile.getTitle());
        if (number > 0L) {
            listenCounter.setText(String.valueOf(number));
            listenCounter.setVisibility(VISIBLE);
        } else {
            listenCounter.setVisibility(GONE);
        }
    }

}