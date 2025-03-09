package com.example.onlineshop;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class InfoActivity extends AppCompatActivity {

    private ImageView btnBackInfo, btnInfo;
    private TextView tvClearCache, tvUpdateVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        addControls();
        handleEvents();
    }

    private void handleEvents() {
        // Nút quay lại ProfileActivity
        btnBackInfo.setOnClickListener(v -> {
            Intent intent = new Intent(InfoActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish();
        });
        // Hiện thông tin ứng dụng khi bấm icon "i"
        btnInfo.setOnClickListener(v ->
                Toast.makeText(this, "Thông tin ứng dụng OnlineShop", Toast.LENGTH_SHORT).show()
        );
        // Hiển thị Dialog xác nhận xóa bộ nhớ
        tvClearCache.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.logo) // Icon logo
                    .setTitle("Xóa Bộ Nhớ")
                    .setMessage("Bạn có chắc muốn xóa bộ nhớ?")
                    .setPositiveButton("XÓA", (dialog, which) -> clearAppCache())
                    .setNegativeButton("HỦY", null)
                    .show();
        });

        // Hiển thị Dialog phiên bản mới nhất
        tvUpdateVersion.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Đây là phiên bản mới nhất")
                    .setMessage("v1.0.0")
                    .setPositiveButton("ĐỒNG Ý", null)
                    .show();
        });
    }

    private void addControls() {
        // Ánh xạ view
        btnBackInfo = findViewById(R.id.btnBackInfo);
        btnInfo = findViewById(R.id.btnInfo);
        tvClearCache = findViewById(R.id.tvClearCache);
        tvUpdateVersion = findViewById(R.id.tvUpdateVersion);
    }
    // Hàm xóa cache ứng dụng
    private void clearAppCache() {
        try {
            File dir = getCacheDir();
            deleteDir(dir);
            Toast.makeText(this, "Bộ nhớ tạm đã được xóa!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Xóa bộ nhớ tạm thất bại!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (String aChildren : children) {
                boolean success = deleteDir(new File(dir, aChildren));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if (dir != null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }

}
