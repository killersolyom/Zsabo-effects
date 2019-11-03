package com.zsabo.effects.Presenter;


import android.view.View;
import android.view.ViewGroup;

import androidx.leanback.widget.Presenter;

import com.zsabo.effects.Communication.AudioStreamInterface;
import com.zsabo.effects.CustomView.AudioItemView;
import com.zsabo.effects.Models.AudioFile;
import com.zsabo.effects.R;


public class AudioItemPresenter extends Presenter {

    private AudioStreamInterface streamInterface;

    public AudioItemPresenter(AudioStreamInterface audioStreamInterface) {
        streamInterface = audioStreamInterface;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new PresenterViewHolder(View.inflate(parent.getContext(), R.layout.audio_presenter_view, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        PresenterViewHolder holder = (PresenterViewHolder) viewHolder;
        holder.bind((AudioFile) item);
        holder.audioItem.onUnBind();
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {
        PresenterViewHolder holder = (PresenterViewHolder) viewHolder;
        streamInterface.unRegistration(holder.audioItem);
    }

    class PresenterViewHolder extends ViewHolder {

        AudioItemView audioItem;

        PresenterViewHolder(View itemView) {
            super(itemView);
            audioItem = itemView.findViewById(R.id.audio_item);
        }

        void bind(AudioFile audioFile) {
            audioItem.onBind(audioFile);
            streamInterface.registration(audioItem);
        }

    }


}
