package com.zsabo.effects.Activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.zsabo.effects.Communication.MainActivityInterface;
import com.zsabo.effects.CustomView.NotificationBar;
import com.zsabo.effects.R;
import com.zsabo.effects.Utilities.DataManager;
import com.zsabo.effects.Utilities.FragmentNavigation;
import com.zsabo.effects.Utilities.GlideUtils;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, MainActivityInterface {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private NotificationBar notificationBar;
    private ImageView background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationBar = findViewById(R.id.notification_bar);
        navigationView = findViewById(R.id.nav_view);
        background = findViewById(R.id.background_image);
        drawerLayout = findViewById(R.id.drawer_layout);
        Glide.with(getApplicationContext()).load(R.drawable.bcg1).centerCrop().into(background);
        initNavigationBar();
        GlideUtils.getInstance().initialize(getApplicationContext());
        DataManager.getInstance().initManager(this);
        FragmentNavigation.getInstance().initComponents(this, this);
        FragmentNavigation.getInstance().showAudioStreamFragment();
    }


    private void initNavigationBar() {
        navigationView.setNavigationItemSelectedListener(this);
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
    }
}
