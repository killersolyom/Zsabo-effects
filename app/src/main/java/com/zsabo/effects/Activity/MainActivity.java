package com.zsabo.effects.Activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.zsabo.effects.R;
import com.zsabo.effects.Utilities.FragmentNavigation;
import com.zsabo.effects.Utilities.DataManager;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        initNavigationBar();

        DataManager.getInstance().initManager(this);
        FragmentNavigation.getInstance().initComponents(this);
        FragmentNavigation.getInstance().showAudioStreamFragment();
    }


    private void initNavigationBar() {
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        FragmentNavigation.getInstance().handleNavigationItem(menuItem,drawerLayout);
        return false;
    }

    @Override
    public void onBackPressed() {
        FragmentNavigation.getInstance().onBackPressed();
    }
}
