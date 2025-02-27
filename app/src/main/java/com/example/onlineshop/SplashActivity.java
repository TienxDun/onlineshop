package com.example.onlineshop;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Áp dụng Animation cho logo
        ImageView imgLogo = findViewById(R.id.imgLogo);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.logo_animation);
        imgLogo.startAnimation(animation);

        // Chuyển sang MainActivity sau 3 giây
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }, 3000); // 3000 ms = 3 giây
    }
}
