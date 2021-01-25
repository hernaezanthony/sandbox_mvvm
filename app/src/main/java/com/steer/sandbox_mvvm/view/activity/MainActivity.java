package com.steer.sandbox_mvvm.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.steer.sandbox_mvvm.base.BaseActivity;
import com.steer.sandbox_mvvm.R;
import com.steer.sandbox_mvvm.view.fragment.HomeFragment;
import com.steer.sandbox_mvvm.view.fragment.ImageListFragment;
import com.steer.sandbox_mvvm.view.fragment.TodoListFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;

    @BindView(R.id.sideNavView)
    NavigationView navigationView;

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUpSideAndBottomNavigation();
        replaceFragment(new HomeFragment()); // Default Fragment

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:

                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    private void setUpSideAndBottomNavigation(){

        //For Side Navigation

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle actionBarDrawerToggle =
                new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

                    private float last = 0;

                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        super.onDrawerSlide(drawerView, slideOffset);

                        boolean opening = slideOffset > last;
                        boolean closing = slideOffset < last;

                        last = slideOffset;
                    }

                };

        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        //For Bottom Navigation

        BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.home:
                        replaceFragment(new HomeFragment());
                        break;

                    case R.id.todoList:
                        replaceFragment(new TodoListFragment());
                        break;

                    case R.id.imageList:
                        replaceFragment(new ImageListFragment());
                        break;
                }

                return true;
            }
        };

        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

    }

    private void replaceFragment(Fragment fragment) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentHost, fragment);
        ft.commit();

    }

}