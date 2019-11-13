package com.zsabo.effects.CustomView.SettingsItem;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.zsabo.effects.Models.SeekBarObjectModel;
import com.zsabo.effects.R;


public class SeekBarView extends ConstraintLayout {

    private TextView title;

    public SeekBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.seek_bar_item_view, this, true);
        title = findViewById(R.id.item_text_view);
    }

    public void onBind(SeekBarObjectModel seekBarObjectModel) {
        title.setText(seekBarObjectModel.getTitle());
    }

    public void unBind() {
    }
}