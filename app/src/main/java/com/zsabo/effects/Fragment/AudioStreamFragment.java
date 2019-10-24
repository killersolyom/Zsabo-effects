package com.zsabo.effects.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ClassPresenterSelector;
import androidx.leanback.widget.ItemBridgeAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.zsabo.effects.Models.AudioFile;
import com.zsabo.effects.Presenter.AudioItemPresenter;
import com.zsabo.effects.R;
import com.zsabo.effects.Utilities.ResourceReader;

import java.util.ArrayList;

public class AudioStreamFragment extends Fragment {

    private View view;
    private ItemBridgeAdapter adapter;
    private RecyclerView soundRecyclerView;
    private ArrayObjectAdapter objectAdapter;
    private AudioItemPresenter audioItemPresenter;
    private ClassPresenterSelector presenterSelector;

    public AudioStreamFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            presenterSelector = new ClassPresenterSelector();
            adapter = new ItemBridgeAdapter();
            objectAdapter = new ArrayObjectAdapter();
            view = inflater.inflate(R.layout.fragment_audio_stream, container, false);
            soundRecyclerView = view.findViewById(R.id.audio_recycler_view);
            soundRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
            initPresenters();
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
        audioItemPresenter = new AudioItemPresenter();
        presenterSelector.addClassPresenter(AudioFile.class, audioItemPresenter);
        return presenterSelector;
    }

}
