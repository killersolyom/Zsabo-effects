package com.zsabo.effects.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.zsabo.effects.R;

public class AudioItemView extends ConstraintLayout {

    private TextView itemTitle;

    public AudioItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.audio_item_view, this, true);
        ImageView itemImage = findViewById(R.id.item_image_view);
        itemTitle = findViewById(R.id.item_title_text_view);
        Glide.with(context).load(R.drawable.launcher_icon).centerCrop().into(itemImage);
    }

    public void setTitle(String title) {
        itemTitle.setText(title);
    }
}