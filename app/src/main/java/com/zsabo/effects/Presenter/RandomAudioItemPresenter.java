package com.zsabo.effects.Presenter;


import android.view.View;
import android.view.ViewGroup;

import androidx.leanback.widget.Presenter;

import com.zsabo.effects.CustomView.AudioItemView;
import com.zsabo.effects.Models.AudioFile;
import com.zsabo.effects.Models.ClickListenerObject;
import com.zsabo.effects.R;


public class RandomAudioItemPresenter extends Presenter {

    public RandomAudioItemPresenter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new PresenterViewHolder(View.inflate(parent.getContext(), R.layout.audio_presenter_view, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        PresenterViewHolder holder = (PresenterViewHolder) viewHolder;
        holder.bind((ClickListenerObject) item);
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {

    }

    class PresenterViewHolder extends ViewHolder {

        AudioItemView audioItem;

        PresenterViewHolder(View itemView) {
            super(itemView);
            audioItem = itemView.findViewById(R.id.audio_item);
        }

        void bind(ClickListenerObject clickListenerObject) {
            audioItem.setOnClickListener(clickListenerObject.getClickListener());
            audioItem.setTitle("Random");
        }
    }


}
