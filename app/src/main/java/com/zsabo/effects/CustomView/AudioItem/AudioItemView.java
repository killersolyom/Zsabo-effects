package com.zsabo.effects.CustomView.AudioItem;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.zsabo.effects.CustomView.Other.BubbleClickAnimator;
import com.zsabo.effects.Models.AudioFile;
import com.zsabo.effects.R;
import com.zsabo.effects.Utilities.GlideUtils;

public class AudioItemView extends ConstraintLayout {

    private ImageView itemImage;
    private Animation clickAnimator;
    private AudioItemTitleView audioTitleView;
    private AudioItemListenCounterView listenCounterView;
    private Animation.AnimationListener clickAnimatorListener;

    public AudioItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.audio_item_view, this, true);
        clickAnimator = AnimationUtils.loadAnimation(getContext(), R.anim.click_animator);
        clickAnimator.setInterpolator(new BubbleClickAnimator(0.15, 25));
        initClickAnimatorListener(this);
        clickAnimator.setAnimationListener(clickAnimatorListener);
        itemImage = findViewById(R.id.item_image_view);
        listenCounterView = findViewById(R.id.item_listen_counter_view);
        audioTitleView = findViewById(R.id.item_title_layout);
    }

    public void onBind(final AudioFile audioFile) {
        GlideUtils.getInstance().loadImage(R.drawable.play_button_icon, itemImage);
        audioTitleView.setTitle(audioFile.getTitle());
        listenCounterView.displayCounter(audioFile);
        addClickListener(audioFile);
    }

    public void onUnBind() {
        GlideUtils.getInstance().clearImage(itemImage);
        audioTitleView.removeTitle();
        this.setOnClickListener(null);
    }

    private void addClickListener(AudioFile audioFile) {
        this.setOnClickListener(view -> onItemSelected(audioFile));
    }

    public Runnable getButtonAction() {
        return this::onClick;
    }

    private void onItemSelected(AudioFile audioFile) {
        if (audioFile.play()) {
            listenCounterView.increaseCounter(audioFile);
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

    public void onClick() {
        this.startAnimation(clickAnimator);
        this.performClick();
    }
}