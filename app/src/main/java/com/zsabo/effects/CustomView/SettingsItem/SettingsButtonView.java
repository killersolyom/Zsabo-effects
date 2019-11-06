package com.zsabo.effects.CustomView.SettingsItem;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.zsabo.effects.Models.SettingsButtonModel;
import com.zsabo.effects.R;

public class SettingsButtonView extends ConstraintLayout {

    private Button button;

    public SettingsButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.settings_button_item_view, this, true);
        button = findViewById(R.id.settings_button);
    }


    private void addButtonRunnable(Runnable runnable) {
        this.setOnClickListener(view -> runnable.run());
    }

    public void setData(SettingsButtonModel settingsButtonModel) {
        addButtonRunnable(settingsButtonModel.getRunnable());
        button.setText(settingsButtonModel.getTitle());
    }
}