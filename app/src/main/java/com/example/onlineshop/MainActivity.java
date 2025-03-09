package com.example.onlineshop;

import android.content.ClipData;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.onlineshop.fragments.HomeFragment;
import com.example.onlineshop.fragments.NotificationsFragment;
import com.example.onlineshop.fragments.ProfileFragment;
import com.example.onlineshop.fragments.WishListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        handleEvents();


    }

    private void handleEvents() {


    }

    private void addControls() {
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);



    }


}