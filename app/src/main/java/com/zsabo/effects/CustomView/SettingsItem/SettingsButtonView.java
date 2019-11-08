package com.zsabo.effects.CustomView.SettingsItem;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.zsabo.effects.Models.SettingsButtonModel;
import com.zsabo.effects.R;


public class SettingsButtonView extends ConstraintLayout {

    private TextView title;
    private int handlingDelay = 300;

    public SettingsButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.settings_button_item_view, this, true);
        title = findViewById(R.id.item_text_view);
    }

    public void onBind(SettingsButtonModel settingsModel) {
        this.setOnClickListener(v -> new Handler().postDelayed(settingsModel.getRunnable(), handlingDelay));
        title.setText(settingsModel.getTitle());
    }

    public void unBind() {
        this.setOnClickListener(null);
    }
}