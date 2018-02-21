package com.example.bertiwi.readiswhatilove.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.bertiwi.readiswhatilove.R;
import com.example.bertiwi.readiswhatilove.fragments.HomeFragment;
import com.example.bertiwi.readiswhatilove.fragments.SearchFragment;
import com.example.bertiwi.readiswhatilove.fragments.SettingsFragment;
import com.example.bertiwi.readiswhatilove.fragments.StarredFragment;
import com.viven.fragmentstatemanager.FragmentStateManager;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private FragmentStateManager mFragmentStateManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.widget.Toolbar appbar = (android.support.v7.widget.Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(appbar);

        getSupportActionBar().setTitle("Home");

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigationbar);

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.content_frame, new HomeFragment());
        tx.commit();

        //Mantenir estat dels fragments
        FrameLayout content = findViewById(R.id.content_frame);

        mFragmentStateManager = new FragmentStateManager(content, getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i){
                    case 0:
                        return new HomeFragment();
                    case 1:
                        return new SearchFragment();
                    case 2:
                        return new StarredFragment();
                    case 3:
                        return new SettingsFragment();
                }
                return null;
            }
        };


        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        boolean fragmentTransaction = false;
                        android.support.v4.app.Fragment selectedFragment = null;

                        switch (item.getItemId()) {
                            case R.id.action_home:
                                /*selectedFragment = new HomeFragment();
                                fragmentTransaction = true;*/
                                mFragmentStateManager.changeFragment(0);
                                break;
                            case R.id.action_search:
                                /*selectedFragment = new SearchFragment();
                                fragmentTransaction = true;*/
                                mFragmentStateManager.changeFragment(1);
                                break;
                            case R.id.action_starred:
                      /*          selectedFragment = new StarredFragment();
                                fragmentTransaction = true;
                      */          mFragmentStateManager.changeFragment(2);
                                break;
                            case R.id.action_settings:
                                /*selectedFragment = new SettingsFragment();
                                fragmentTransaction = true;*/
                                mFragmentStateManager.changeFragment(3);
                                break;
                        }
                        /*if (fragmentTransaction){
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.content_frame, selectedFragment)
                                    .commit();*/

                            //bottomNavigationView.setItemIconTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorWhite)));
                            //item.setChecked(true);}
                            getSupportActionBar().setTitle(item.getTitle());


                        return true;
                    }
                });
    }
}
