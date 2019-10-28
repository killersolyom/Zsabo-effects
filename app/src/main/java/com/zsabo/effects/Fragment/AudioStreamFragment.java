package com.zsabo.effects.Fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ClassPresenterSelector;
import androidx.leanback.widget.ItemBridgeAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zsabo.effects.Communication.AudioStreamItemInterface;
import com.zsabo.effects.Models.AudioFile;
import com.zsabo.effects.Presenter.AudioItemPresenter;
import com.zsabo.effects.R;
import com.zsabo.effects.Utilities.AudioPlayerManager;
import com.zsabo.effects.Utilities.ResourceReader;

public class AudioStreamFragment extends Fragment implements AudioStreamItemInterface {

    private View view;
    private int portraitColumnNumber = 3;
    private int landscapeColumnNumber = 4;
    private ItemBridgeAdapter adapter;
    private AudioPlayerManager audioPlayer;
    private RecyclerView soundRecyclerView;
    private ArrayObjectAdapter objectAdapter;
    private ClassPresenterSelector presenterSelector;

    public AudioStreamFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            presenterSelector = new ClassPresenterSelector();
            adapter = new ItemBridgeAdapter();
            objectAdapter = new ArrayObjectAdapter();
            audioPlayer = new AudioPlayerManager(getContext());
            view = inflater.inflate(R.layout.fragment_audio_stream, container, false);
            soundRecyclerView = view.findViewById(R.id.audio_recycler_view);
            initPresenters();
        }

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            soundRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), portraitColumnNumber));
        } else {
            soundRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), landscapeColumnNumber));
        }

        return view;
    }

    private void initPresenters() {
        presenterSelector = setUpPresenter();
        fillAdapter(objectAdapter);
        adapter.setPresenter(presenterSelector);
        adapter.setAdapter(objectAdapter);
        soundRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void fillAdapter(ArrayObjectAdapter objectAdapter) {
        for (AudioFile it : ResourceReader.getInstance().getAudioFiles(getContext())) {
            objectAdapter.add(it);
        }
    }

    private ClassPresenterSelector setUpPresenter() {
        AudioItemPresenter audioItemPresenter = new AudioItemPresenter(this);
        presenterSelector.addClassPresenter(AudioFile.class, audioItemPresenter);
        return presenterSelector;
    }

    @Override
    public void onItemClick(AudioFile audioFile) {
        audioPlayer.playAudio(audioFile);
    }
}
