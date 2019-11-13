package com.zsabo.effects.Fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ClassPresenterSelector;
import androidx.leanback.widget.ItemBridgeAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zsabo.effects.Models.AudioFile;
import com.zsabo.effects.Models.RunnableObjectModel;
import com.zsabo.effects.Presenter.AudioItemPresenter;
import com.zsabo.effects.Presenter.RandomAudioItemPresenter;
import com.zsabo.effects.R;
import com.zsabo.effects.Utilities.GlideUtils;
import com.zsabo.effects.Utilities.ResourceReader;

public class AudioStreamFragment extends Fragment {

    private View view;
    private final int portraitColumnNumber = 3;
    private final int landscapeColumnNumber = 5;
    private ItemBridgeAdapter adapter;
    private ImageView background;
    private RecyclerView soundRecyclerView;
    private ArrayObjectAdapter objectAdapter;
    private GridLayoutManager layoutManager;
    private Animation slideUpAnimation;
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
            slideUpAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_up_animation);
            view = inflater.inflate(R.layout.fragment_audio_stream, container, false);
            layoutManager = new GridLayoutManager(this.getContext(), portraitColumnNumber);
            soundRecyclerView = view.findViewById(R.id.audio_recycler_view);
            background = view.findViewById(R.id.setting_fragment_background);
            soundRecyclerView.setLayoutManager(layoutManager);
            initPresenters();
        }
        handleRotation();
        return view;
    }

    private void initPresenters() {
        presenterSelector = setUpPresenter();
        fillAdapter(objectAdapter);
        adapter.setPresenter(presenterSelector);
        adapter.setAdapter(objectAdapter);
        soundRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        soundRecyclerView.startAnimation(slideUpAnimation);
    }

    private void fillAdapter(ArrayObjectAdapter objectAdapter) {
        for (AudioFile it : ResourceReader.getInstance().getAudioFiles(getContext())) {
            objectAdapter.add(it);
        }
    }

    private ClassPresenterSelector setUpPresenter() {
        AudioItemPresenter audioItemPresenter = new AudioItemPresenter();
        RandomAudioItemPresenter randomAudioItemPresenter = new RandomAudioItemPresenter();
        presenterSelector.addClassPresenter(AudioFile.class, audioItemPresenter);
        presenterSelector.addClassPresenter(RunnableObjectModel.class, randomAudioItemPresenter);
        return presenterSelector;
    }

    private void handleRotation() {
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager.setSpanCount(portraitColumnNumber);
            GlideUtils.getInstance().changeBackgroundWithFadeOut(R.drawable.bcg2, background);
        } else {
            layoutManager.setSpanCount(landscapeColumnNumber);
            GlideUtils.getInstance().changeBackgroundWithFadeOut(R.drawable.bcg3, background);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        handleRotation();
        soundRecyclerView.startAnimation(slideUpAnimation);
    }

    @Override
    public void onPause() {
        super.onPause();
        GlideUtils.getInstance().clearImage(background);
    }
}
