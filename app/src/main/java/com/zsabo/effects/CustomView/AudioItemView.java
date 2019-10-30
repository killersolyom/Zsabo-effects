package com.zsabo.effects.CustomView;

import android.app.Application;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.zsabo.effects.Models.AudioFile;
import com.zsabo.effects.R;
import com.zsabo.effects.Utilities.DataManager;

public class AudioItemView extends ConstraintLayout {

    private TextView itemTitle;
    private ImageView itemImage;
    private TextView listenCounter;

    public AudioItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.audio_item_view, this, true);
        itemImage = findViewById(R.id.item_image_view);
        itemTitle = findViewById(R.id.item_title_text_view);
        listenCounter = findViewById(R.id.item_listen_counter);
        try {
            Glide.with(getApplicationUsingReflection().getApplicationContext()).load(R.drawable.launcher_icon).centerCrop().into(itemImage);
        } catch (Exception ignored) {
        }
    }

    public void setData(final AudioFile audioFile) {
        itemTitle.setText(audioFile.getTitle());
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

    private Application getApplicationUsingReflection() throws Exception {
        return (Application) Class.forName("android.app.AppGlobals").getMethod("getInitialApplication").invoke(null, (Object[]) null);
    }

    public void setTitle(String title) {
        itemTitle.setText(title);
    }

    public void onClick() {
        this.performClick();
    }
}