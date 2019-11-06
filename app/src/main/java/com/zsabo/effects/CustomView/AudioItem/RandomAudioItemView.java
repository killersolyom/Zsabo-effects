package com.zsabo.effects.CustomView.AudioItem;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.zsabo.effects.R;
import com.zsabo.effects.Utilities.GlideUtils;

public class RandomAudioItemView extends ConstraintLayout {

    public RandomAudioItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.random_audio_item_view, this, true);
        ImageView itemImage = findViewById(R.id.item_image_view);
        GlideUtils.getInstance().loadImage(R.drawable.random_item_image, itemImage);
    }


    public void setRunnable(Runnable runnable) {
        this.setOnClickListener(view -> runnable.run());
    }
}