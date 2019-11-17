package com.zsabo.effects.CustomView.SettingsItem;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.zsabo.effects.Models.SeekBarObjectModel;
import com.zsabo.effects.R;
import com.zsabo.effects.Utilities.DataManager;


public class SeekBarView extends ConstraintLayout {

    private TextView title;
    private SeekBar seekBar;

    public SeekBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.seek_bar_item_view, this, true);
        title = findViewById(R.id.item_text_view);
        seekBar = findViewById(R.id.item_seek_bar);
    }

    public void onBind(SeekBarObjectModel seekBarObjectModel) {
        title.setText(seekBarObjectModel.getTitle());
        seekBar.setProgress(DataManager.getInstance().getAlphaValue());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarObjectModel.mInterface.onValueChanged(seekBarObjectModel.getMessageKey(), progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void unBind() {
        title.setText("");
        seekBar.setOnSeekBarChangeListener(null);
    }
}