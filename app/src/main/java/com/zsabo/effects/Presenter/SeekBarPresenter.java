package com.zsabo.effects.Presenter;


import android.view.View;
import android.view.ViewGroup;

import androidx.leanback.widget.Presenter;

import com.zsabo.effects.CustomView.SettingsItem.SeekBarView;
import com.zsabo.effects.Models.SeekBarObjectModel;
import com.zsabo.effects.R;


public class SeekBarPresenter extends Presenter {


    public SeekBarPresenter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new PresenterViewHolder(View.inflate(parent.getContext(), R.layout.seek_bar_view, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        PresenterViewHolder holder = (PresenterViewHolder) viewHolder;
        holder.bind((SeekBarObjectModel) item);
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {
        PresenterViewHolder holder = (PresenterViewHolder) viewHolder;
        holder.seekBarView.unBind();
    }

    class PresenterViewHolder extends ViewHolder {

        SeekBarView seekBarView;

        PresenterViewHolder(View itemView) {
            super(itemView);
            seekBarView = itemView.findViewById(R.id.seek_bar_item);
        }

        void bind(SeekBarObjectModel seekBarObjectModel) {
            seekBarView.onBind(seekBarObjectModel);
        }

    }

}
