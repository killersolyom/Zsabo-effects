package com.zsabo.effects.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.zsabo.effects.Models.AudioFile;
import com.zsabo.effects.R;
import com.zsabo.effects.Utilities.DataManager;
import com.zsabo.effects.Utilities.GlideUtils;

public class AudioItemView extends ConstraintLayout {

    private ImageView itemImage;
    private TextView listenCounter;
    private Animation clickAnimator;
    private AudioItemTitleView audioTitleView;
    private Animation.AnimationListener clickAnimatorListener;

    public AudioItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.audio_item_view, this, true);
        clickAnimator = AnimationUtils.loadAnimation(getContext(), R.anim.click_animator);
        initClickAnimatorListener(this);
        clickAnimator.setAnimationListener(clickAnimatorListener);
        itemImage = findViewById(R.id.item_image_view);
        listenCounter = findViewById(R.id.item_listen_counter);
        audioTitleView = findViewById(R.id.item_title_layout);
    }

    public void onBind(final AudioFile audioFile) {

        GlideUtils.getInstance().loadImage(R.drawable.play_button_icon, itemImage);
        audioTitleView.setTitle(audioFile.getTitle());
        setCounterNumber(audioFile);
        addClickListener(audioFile);
    }

    public void onUnBind() {
        if (itemImage.getDrawable() == getResources().getDrawable(R.drawable.play_button_icon)) {
            GlideUtils.getInstance().clearImage(itemImage);
            audioTitleView.removeTitle();
            this.setOnClickListener(null);
        }
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

    public String getTitle() {
        return audioTitleView.getText();
    }

    private void initClickAnimatorListener(AudioItemView audioItemView) {
        clickAnimatorListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                audioItemView.callOnClick();
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
    }

    public void OnClick() {
        this.startAnimation(clickAnimator);
    }
}