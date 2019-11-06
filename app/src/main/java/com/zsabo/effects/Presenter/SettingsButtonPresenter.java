package com.zsabo.effects.Presenter;


import android.view.View;
import android.view.ViewGroup;

import androidx.leanback.widget.Presenter;

import com.zsabo.effects.CustomView.SettingsItem.SettingsButtonView;
import com.zsabo.effects.Models.SettingsButtonModel;
import com.zsabo.effects.R;


public class SettingsButtonPresenter extends Presenter {


    public SettingsButtonPresenter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new PresenterViewHolder(View.inflate(parent.getContext(), R.layout.setting_button_view, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        PresenterViewHolder holder = (PresenterViewHolder) viewHolder;
        holder.bind((SettingsButtonModel) item);
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {
        PresenterViewHolder holder = (PresenterViewHolder) viewHolder;
    }

    class PresenterViewHolder extends ViewHolder {

        SettingsButtonView SettingsButtonView;

        PresenterViewHolder(View itemView) {
            super(itemView);
            SettingsButtonView = itemView.findViewById(R.id.settings_button_item);
        }

        void bind(SettingsButtonModel settingsButtonModel) {
            SettingsButtonView.setData(settingsButtonModel);

        }

    }


}