package com.zsabo.effects.Presenter;


import android.view.View;
import android.view.ViewGroup;

import androidx.leanback.widget.Presenter;

import com.zsabo.effects.CustomView.AudioItem.RandomAudioItemView;
import com.zsabo.effects.Models.RunnableObjectModel;
import com.zsabo.effects.R;


public class RandomAudioItemPresenter extends Presenter {

    public RandomAudioItemPresenter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new PresenterViewHolder(View.inflate(parent.getContext(), R.layout.random_audio_presenter_view, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        PresenterViewHolder holder = (PresenterViewHolder) viewHolder;
        holder.bind(((RunnableObjectModel) item).getRunnable());
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {

    }

    class PresenterViewHolder extends ViewHolder {

        RandomAudioItemView randomAudioItem;

        PresenterViewHolder(View itemView) {
            super(itemView);
            randomAudioItem = itemView.findViewById(R.id.random_audio_item);
        }

        void bind(Runnable runnable) {
            randomAudioItem.setRunnable(runnable);
        }
    }


}
