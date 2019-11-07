package com.zsabo.effects.CustomView.SettingsItem;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.zsabo.effects.R;

public class SettingsButtonView extends ConstraintLayout {

    public SettingsButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.settings_button_item_view, this, true);
    }
}