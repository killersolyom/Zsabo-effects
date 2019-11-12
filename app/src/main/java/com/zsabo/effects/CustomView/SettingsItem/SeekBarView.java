package com.zsabo.effects.CustomView.SettingsItem;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.zsabo.effects.Models.SeekBarObjectModel;
import com.zsabo.effects.R;


public class SeekBarView extends ConstraintLayout {

    private TextView titleView;
    private SeekBar seekBar;

    public SeekBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.seek_bar_button_item_view, this, true);
        titleView = findViewById(R.id.title_view);
        seekBar = findViewById(R.id.seek_view);
    }

    public void onBind(SeekBarObjectModel seekbarObjectModel) {
        titleView.setText(seekbarObjectModel.getTitle());
    }

    public void onUnBind() {
    }
}