package com.zsabo.effects.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zsabo.effects.R;
import com.zsabo.effects.Utilities.DataManager;
import com.zsabo.effects.Utilities.GlideUtils;
import com.zsabo.effects.Utilities.ResourceReader;

public class SettingsFragment extends Fragment {

    private Button clearCounterButton;
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
            view = inflater.inflate(R.layout.fragment_settings, container, false);
            clearCounterButton = view.findViewById(R.id.reset_settings_button);
            background = view.findViewById(R.id.setting_fragment_background);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        addClearButtonClickListener();
    }

    private void addClearButtonClickListener() {
        clearCounterButton.setOnClickListener(view -> clearAllListenCounter());
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
