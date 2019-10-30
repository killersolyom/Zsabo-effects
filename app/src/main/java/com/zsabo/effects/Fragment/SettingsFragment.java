package com.zsabo.effects.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zsabo.effects.R;
import com.zsabo.effects.Utilities.DataManager;
import com.zsabo.effects.Utilities.ResourceReader;

import java.util.Objects;

public class SettingsFragment extends Fragment {

    private Button clearCounterButton;
    private View view;

    public SettingsFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_settings, container, false);
            clearCounterButton = view.findViewById(R.id.reset_settings_button);
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
        ResourceReader
                .getInstance()
                .getAudioFiles(getContext())
                .stream()
                .filter(Objects::nonNull)
                .forEach(s -> DataManager.getInstance().resetCounter(s.getTitle()));
    }
}