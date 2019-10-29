package com.zsabo.effects.CustomView;

import android.app.Application;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.zsabo.effects.Activity.MainActivity;
import com.zsabo.effects.Models.AudioFile;
import com.zsabo.effects.R;

public class AudioItemView extends ConstraintLayout {

    private TextView itemTitle;
    private ImageView itemImage;

    public AudioItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.audio_item_view, this, true);
        itemImage = findViewById(R.id.item_image_view);
        itemTitle = findViewById(R.id.item_title_text_view);
        try {
            Glide.with(getApplicationUsingReflection().getApplicationContext()).load(R.drawable.launcher_icon).centerCrop().into(itemImage);
        } catch (Exception ignored) {
        }
    }

    public void setData(final AudioFile audioFile) {
        itemTitle.setText(audioFile.getTitle());
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                audioFile.play();
            }
        });
    }

    private Application getApplicationUsingReflection() throws Exception {
        return (Application) Class.forName("android.app.AppGlobals").getMethod("getInitialApplication").invoke(null, (Object[]) null);
    }

}