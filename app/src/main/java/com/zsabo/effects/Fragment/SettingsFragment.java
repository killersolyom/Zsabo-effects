package com.zsabo.effects.Fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ClassPresenterSelector;
import androidx.leanback.widget.ItemBridgeAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zsabo.effects.Communication.SettingsFragmentInterface;
import com.zsabo.effects.Models.SeekBarObjectModel;
import com.zsabo.effects.Models.SettingsButtonModel;
import com.zsabo.effects.Presenter.SeekBarPresenter;
import com.zsabo.effects.Presenter.SettingsButtonPresenter;
import com.zsabo.effects.R;
import com.zsabo.effects.Utilities.DataManager;
import com.zsabo.effects.Utilities.FragmentNavigation;
import com.zsabo.effects.Utilities.GlideUtils;
import com.zsabo.effects.Utilities.ResourceReader;

public class SettingsFragment extends Fragment implements SettingsFragmentInterface {

    private View view;
    private ImageView background;
    private ItemBridgeAdapter adapter;
    private Animation slideUpAnimation;
    private GridLayoutManager layoutManager;
    private ArrayObjectAdapter objectAdapter;
    private RecyclerView settingsRecyclerView;
    private final int portraitColumnNumber = 1;
    private final int landscapeColumnNumber = 2;
    private final String alphaKey = "ALPHA_KEY";
    private ClassPresenterSelector presenterSelector;

    public SettingsFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            slideUpAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_up_animation);
            view = inflater.inflate(R.layout.fragment_settings, container, false);
            settingsRecyclerView = view.findViewById(R.id.settings_recycler_view);
            layoutManager = new GridLayoutManager(this.getContext(), 1);
            settingsRecyclerView.setLayoutManager(layoutManager);
            background = view.findViewById(R.id.setting_fragment_background);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    }

    private void initPresenters() {
        presenterSelector = new ClassPresenterSelector();
        adapter = new ItemBridgeAdapter();
        objectAdapter = new ArrayObjectAdapter();
        presenterSelector = setUpPresenter();
        setUpFragmentItems();
        adapter.setPresenter(presenterSelector);
        adapter.setAdapter(objectAdapter);
        settingsRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        settingsRecyclerView.startAnimation(slideUpAnimation);
    }

    private void setUpFragmentItems() {
        if (getContext() != null) {
            objectAdapter.add(new SettingsButtonModel(this::clearAllListenCounter, getContext().getString(R.string.clear_listen_counters)));
            objectAdapter.add(new SeekBarObjectModel(getContext().getString(R.string.item_alpha), alphaKey, this));
        }
    }

    private ClassPresenterSelector setUpPresenter() {
        presenterSelector.addClassPresenter(SettingsButtonModel.class, new SettingsButtonPresenter());
        presenterSelector.addClassPresenter(SeekBarObjectModel.class, new SeekBarPresenter());
        return presenterSelector;
    }


    private void clearAllListenCounter() {
        for (String it : ResourceReader.getInstance().getAudioFileNames(getContext())) {
            DataManager.getInstance().resetCounter(it);
        }
        if (getContext() != null) {
            FragmentNavigation.getInstance().showNotificationBar(
                    getContext().getString(R.string.finished),
                    getContext().getString(R.string.successfully_cleared),
                    getContext().getDrawable(R.drawable.success_image),
                    false);
        }
    }

    private void handleRotation() {
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager.setSpanCount(portraitColumnNumber);
            GlideUtils.getInstance().changeBackgroundWithFadeOut(R.drawable.bcg1, background);
        } else {
            layoutManager.setSpanCount(landscapeColumnNumber);
            GlideUtils.getInstance().changeBackgroundWithFadeOut(R.drawable.bcg4, background);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initPresenters();
        handleRotation();
    }

    @Override
    public void onPause() {
        super.onPause();
        GlideUtils.getInstance().clearImage(background);
    }

    @Override
    public void onValueChanged(String key, int value) {
        switch (key) {
            case alphaKey:
                DataManager.getInstance().setAlphaValue((int) ((value + 15f) / 1.15f));
                Log.d(SettingsFragment.class.getCanonicalName(), "onValueChanged ALPHA: " + value);
                break;
            default:
                Log.d(SettingsFragment.class.getCanonicalName(), "Unknown value");
        }

    }
}
