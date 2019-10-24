package com.zsabo.effects.Utilities;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.zsabo.effects.Activity.MainActivity;
import com.zsabo.effects.Fragment.AudioStreamFragment;
import com.zsabo.effects.R;

public class FragmentNavigation {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private AudioStreamFragment audioStreamFragment;


    private static final FragmentNavigation ourInstance = new FragmentNavigation();

    public static FragmentNavigation getInstance() {
        return ourInstance;
    }

    private FragmentNavigation() {
    }

    public void initComponents(MainActivity activity) {
        fragmentManager = activity.getSupportFragmentManager();
        audioStreamFragment = new AudioStreamFragment();
    }

    public void showAudioStreamFragment() {
        showFragment(audioStreamFragment, false);
    }

    private void showFragment(Fragment fragment, boolean addToBackStack) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment, fragment.getTag());
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getTag());
        }
        fragmentTransaction.commit();
    }

    public void popBackStack() {
        fragmentManager.popBackStack();
    }

}