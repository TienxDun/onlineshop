package com.example.onlineshop;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.onlineshop.fragments.HomeFragment;
import com.example.onlineshop.fragments.NotificationsFragment;
import com.example.onlineshop.fragments.ProfileFragment;
import com.example.onlineshop.fragments.WishListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        handleEvents();

        // Mặc định hiển thị HomeFragment khi mở app
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
        }
    }

    private void addControls() {
        bottomNav = findViewById(R.id.bottom_navigation);
    }

    private void handleEvents() {
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.nav_home) {
                selectedFragment = new HomeFragment();
            } else if (item.getItemId() == R.id.nav_notifications) {
                selectedFragment = new NotificationsFragment();
            } else if (item.getItemId() == R.id.nav_wishlist) {
                selectedFragment = new WishListFragment();
            } else if (item.getItemId() == R.id.nav_profile) {
                selectedFragment = new ProfileFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }
            return true;
        });
    }



}
