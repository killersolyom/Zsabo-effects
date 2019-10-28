package com.zsabo.effects.Utilities;

import android.util.Log;
import android.view.MenuItem;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.zsabo.effects.Activity.MainActivity;
import com.zsabo.effects.Fragment.AudioStreamFragment;
import com.zsabo.effects.Fragment.SettingsFragment;
import com.zsabo.effects.R;

public class FragmentNavigation {

    private FragmentManager fragmentManager;


    private static final FragmentNavigation ourInstance = new FragmentNavigation();

    public static FragmentNavigation getInstance() {
        return ourInstance;
    }

    private FragmentNavigation() {
    }

    public void initComponents(MainActivity activity) {
        fragmentManager = activity.getSupportFragmentManager();
    }

    public void showAudioStreamFragment() {
        clearBackStack();
        Log.d("3ss","showAudioStreamFragment " + fragmentManager.getFragments().size());
        showFragment(new AudioStreamFragment());
    }

    private void showSettingsFragment() {
        clearBackStack();
        showFragment(new SettingsFragment());
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment, fragment.getTag());
        fragmentTransaction.addToBackStack(fragment.getTag());
        fragmentTransaction.commit();
    }

    private void clearBackStack() {
        if(fragmentManager.getFragments().size() == 1){
            return;
        }
        for (int i = 1; i < fragmentManager.getBackStackEntryCount(); ++i) {
            fragmentManager.popBackStack();
        }
    }

    private Fragment getTopFragment() {
        int index = fragmentManager.getFragments().size() - 1;
        return fragmentManager.getFragments().get(index);
    }

    private void exit() {
        clearBackStack();
        System.exit(0);
    }

    public void popBackStack() {
        fragmentManager.popBackStack();
    }

    public void handleNavigationItem(MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_sounds:
                showAudioStreamFragment();
                break;
            case R.id.nav_settings: ;
                showSettingsFragment();
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_exit:
                exit();
                break;
        }
    }

    public void onBackPressed() {
        if (getTopFragment() instanceof AudioStreamFragment) {
            exit();
        } else {
            popBackStack();
        }
    }
}