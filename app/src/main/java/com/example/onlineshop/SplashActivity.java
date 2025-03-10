package com.example.onlineshop;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Đặt chế độ sáng (Light mode)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_splash);

        ImageView profileImage = findViewById(R.id.imgLogo);

        // Hiệu ứng fade-in bằng ObjectAnimator
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(profileImage, "alpha", 0f, 1f);
        fadeIn.setDuration(1000);
        fadeIn.start();

        // Chuyển sang MainActivity sau 3 giây
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }, 1500); // 3000 ms = 3 giây
    }
}
