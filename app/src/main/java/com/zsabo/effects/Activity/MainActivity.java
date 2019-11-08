package com.zsabo.effects.Activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.zsabo.effects.Communication.MainActivityInterface;
import com.zsabo.effects.CustomView.Other.NotificationBar;
import com.zsabo.effects.R;
import com.zsabo.effects.Utilities.DataManager;
import com.zsabo.effects.Utilities.FragmentNavigation;
import com.zsabo.effects.Utilities.GlideUtils;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, MainActivityInterface {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private NotificationBar notificationBar;
    private ImageView drawerHeaderImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationBar = findViewById(R.id.notification_bar);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        FragmentNavigation.getInstance().initComponents(this, this);
        GlideUtils.getInstance().initialize(getApplicationContext());
        DataManager.getInstance().initManager(this);

        if (savedInstanceState == null) {
            FragmentNavigation.getInstance().showAudioStreamFragment();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        FragmentNavigation.getInstance().handleNavigationItem(menuItem, drawerLayout);
        return false;
    }

    @Override
    public void onBackPressed() {
        FragmentNavigation.getInstance().onBackPressed();
    }

    @Override
    public void showNotificationBar(String title, String message, Object image, boolean isError) {
        notificationBar.showNotificationBar(title, message, image, isError);
    }

    @Override
    protected void onPause() {
        super.onPause();
        notificationBar.clearAllTask();
        GlideUtils.getInstance().clearImage(drawerHeaderImage);
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.setNavigationItemSelectedListener(this);
        drawerHeaderImage = (navigationView.getHeaderView(0)).findViewById(R.id.drawer_menu_image);
        GlideUtils.getInstance().loadBackgroundImage(R.drawable.drawer_image, drawerHeaderImage);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("Rotation", "ROTATED");
    }
}
