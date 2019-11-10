package com.zsabo.effects.Fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ClassPresenterSelector;
import androidx.leanback.widget.ItemBridgeAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zsabo.effects.Communication.AudioStreamInterface;
import com.zsabo.effects.Models.AudioFile;
import com.zsabo.effects.Models.RunnableObjectModel;
import com.zsabo.effects.Presenter.AudioItemPresenter;
import com.zsabo.effects.Presenter.RandomAudioItemPresenter;
import com.zsabo.effects.R;
import com.zsabo.effects.Utilities.GlideUtils;
import com.zsabo.effects.Utilities.ResourceReader;

import java.util.ArrayList;
import java.util.Random;

public class AudioStreamFragment extends Fragment implements AudioStreamInterface {

    private View view;
    private Random random;
    private final int portraitColumnNumber = 3;
    private final int landscapeColumnNumber = 5;
    private ItemBridgeAdapter adapter;
    private ImageView background;
    private RecyclerView soundRecyclerView;
    private ArrayObjectAdapter objectAdapter;
    private ArrayList<Runnable> audioItemAction;
    private GridLayoutManager layoutManager;
    private ClassPresenterSelector presenterSelector;
    private String TAG = AudioStreamFragment.class.getCanonicalName();

    public AudioStreamFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            random = new Random();
            audioItemAction = new ArrayList<>();
            presenterSelector = new ClassPresenterSelector();
            adapter = new ItemBridgeAdapter();
            objectAdapter = new ArrayObjectAdapter();
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
    }

    private void fillAdapter(ArrayObjectAdapter objectAdapter) {
        for (AudioFile it : ResourceReader.getInstance().getAudioFiles(getContext())) {
            objectAdapter.add(it);
        }
        if (objectAdapter.size() != 0) {
            objectAdapter.add(new RunnableObjectModel(this::playRandomItem));
        }
    }

    private ClassPresenterSelector setUpPresenter() {
        AudioItemPresenter audioItemPresenter = new AudioItemPresenter(this);
        RandomAudioItemPresenter randomAudioItemPresenter = new RandomAudioItemPresenter();
        presenterSelector.addClassPresenter(AudioFile.class, audioItemPresenter);
        presenterSelector.addClassPresenter(RunnableObjectModel.class, randomAudioItemPresenter);
        return presenterSelector;
    }

    private void playRandomItem() {
        audioItemAction.get(random.nextInt(audioItemAction.size())).run();
    }

    private void handleRotation() {
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager.setSpanCount(portraitColumnNumber);
        } else {
            layoutManager.setSpanCount(landscapeColumnNumber);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        GlideUtils.getInstance().loadBackgroundImage(R.drawable.bcg2, background);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void register(Runnable audioItem) {
        audioItemAction.add(audioItem);
    }

    @Override
    public void onPause() {
        super.onPause();
        GlideUtils.getInstance().clearImage(background);
    }
}
