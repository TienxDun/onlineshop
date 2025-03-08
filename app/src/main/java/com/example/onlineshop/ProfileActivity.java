package com.example.onlineshop;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {

    private ImageView imgProfile;
    private ImageView btnEditProfile;
    private LinearLayout layoutLogout,layoutNotifications, layoutFAQ, layoutAppInfo;
    private TextView tvProfileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        addControls();
        handleEvents();
    }

    private void handleEvents() {
        layoutAppInfo.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, InfoActivity.class);
            startActivity(intent);
        });
        layoutFAQ.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, ChatSupportActivity.class);
            startActivity(intent);
        });
        layoutNotifications.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, NotificationsActivity.class);
            startActivity(intent);
        });
        layoutLogout.setOnClickListener(v -> showLogoutDialog());
        btnEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 100);
        });
    }

    private void addControls() {
        imgProfile = findViewById(R.id.imgProfile);
        btnEditProfile = findViewById(R.id.btnEditProfile);
        layoutLogout = findViewById(R.id.layoutLogout);
        tvProfileName = findViewById(R.id.tvProfileName);
        layoutNotifications = findViewById(R.id.layoutNotifications);
        layoutFAQ = findViewById(R.id.layoutFAQ);
        layoutAppInfo = findViewById(R.id.layoutAppInfo);

        // Lấy dữ liệu username từ Intent
        String username = getIntent().getStringExtra("username");
        if (username != null){
            tvProfileName.setText(username);
        }
    }
    private void showLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn chắc chắn muốn đăng xuất?")
                .setPositiveButton("ĐỒNG Ý", (dialog, which) -> {
                    // Xóa dữ liệu đăng nhập khỏi SharedPreferences
                    getSharedPreferences("USER_PREF", MODE_PRIVATE)
                            .edit()
                            .remove("USERNAME")
                            .apply();
                    // Xử lý logout
                    Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton("KHÔNG", (dialog, which) -> dialog.dismiss());

        // Tạo và hiển thị AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();

        // Lấy nút Positive và Negative sau khi dialog hiển thị
        Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);

        // Đổi màu chữ
        negativeButton.setTextColor(ContextCompat.getColor(this, android.R.color.darker_gray));
        positiveButton.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                imgProfile.setImageBitmap(getCircularBitmap(bitmap));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Cắt ảnh thành hình tròn
    private Bitmap getCircularBitmap(Bitmap bitmap) {
        int size = Math.min(bitmap.getWidth(), bitmap.getHeight());
        Bitmap output = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        Rect rect = new Rect(0, 0, size, size);
        RectF rectF = new RectF(rect);

        canvas.drawOval(rectF, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

}
