package com.zsabo.effects.Presenter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.leanback.widget.Presenter;

import com.bumptech.glide.Glide;
import com.zsabo.effects.Models.AudioFile;
import com.zsabo.effects.R;


public class AudioItemPresenter extends Presenter {

    private Context context;

    public AudioItemPresenter() {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        context = parent.getContext();
        return new PresenterViewHolder(View.inflate(parent.getContext(), R.layout.audio_item_view, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        PresenterViewHolder holder = (PresenterViewHolder) viewHolder;
        holder.bind((AudioFile) item);
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {

    }

    class PresenterViewHolder extends ViewHolder {

        private ConstraintLayout itemLayout;
        private ImageView itemImage;
        private TextView itemTitle;

        PresenterViewHolder(View itemView) {
            super(itemView);
            itemLayout = itemView.findViewById(R.id.item_layout);
            itemImage = itemView.findViewById(R.id.item_image_view);
            itemTitle = itemView.findViewById(R.id.item_title_text_view);
        }

        void bind(AudioFile audioFile) {
            if (audioFile != null) {
                Glide.with(context).load(R.drawable.launcher_icon).centerCrop().into(itemImage);
                addClickListener(audioFile);
                itemTitle.setText(audioFile.getTitle());
            }
        }

        private void addClickListener(final AudioFile audioFile) {
            itemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    audioFile.getAudioFile().start();
                }
            });
        }

    }
}
