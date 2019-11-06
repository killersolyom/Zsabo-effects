package com.zsabo.effects.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ClassPresenterSelector;
import androidx.leanback.widget.ItemBridgeAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zsabo.effects.Models.SettingsButtonModel;
import com.zsabo.effects.Presenter.SettingsButtonPresenter;
import com.zsabo.effects.R;
import com.zsabo.effects.Utilities.DataManager;
import com.zsabo.effects.Utilities.GlideUtils;
import com.zsabo.effects.Utilities.ResourceReader;

public class SettingsFragment extends Fragment {

    private RecyclerView settingsRecyclerView;
    private ItemBridgeAdapter adapter;
    private ClassPresenterSelector presenterSelector;
    private ArrayObjectAdapter objectAdapter;
    private ImageView background;
    private View view;

    public SettingsFragment() {
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
            view = inflater.inflate(R.layout.fragment_settings, container, false);
            settingsRecyclerView = view.findViewById(R.id.settings_recycler_view);
            settingsRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 1));
            background = view.findViewById(R.id.setting_fragment_background);
            initPresenters();
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    }

    private void initPresenters() {
        if (getContext() == null) {
            return;
        }
        presenterSelector = setUpPresenter();
        setUpFragmentItems();
        adapter.setPresenter(presenterSelector);
        adapter.setAdapter(objectAdapter);
        settingsRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setUpFragmentItems() {
        objectAdapter.add(new SettingsButtonModel(this::clearAllListenCounter, getContext().getString(R.string.clear_listen_counters)));
    }

    private ClassPresenterSelector setUpPresenter() {
        SettingsButtonPresenter settingsButtonPresenter = new SettingsButtonPresenter();
        presenterSelector.addClassPresenter(SettingsButtonModel.class, settingsButtonPresenter);
        return presenterSelector;
    }


    private void clearAllListenCounter() {
        for (String it : ResourceReader.getInstance().getAudioFileNames(getContext())) {
            DataManager.getInstance().resetCounter(it);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        GlideUtils.getInstance().loadBackgroundImage(R.drawable.bcg1, background);
    }

    @Override
    public void onPause() {
        super.onPause();
        GlideUtils.getInstance().clearImage(background);
    }
}
