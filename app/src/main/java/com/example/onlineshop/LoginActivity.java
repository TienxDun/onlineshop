package com.example.onlineshop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsername, edtPassword;
    private Button btnLogin;
    private TextView tvCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        addControls();
        addHandles();
        // Kiểm tra tài khoản đã đăng nhập trước đó
        String savedUsername = getSharedPreferences("USER_PREF", MODE_PRIVATE)
                .getString("USERNAME", null);

        if (savedUsername != null) {
            // Chuyển thẳng vào ProfileActivity nếu đã đăng nhập
            Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
            intent.putExtra("username", savedUsername);
            startActivity(intent);
            finish();
        }
    }

    private void addControls() {
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword_Login);
        btnLogin = findViewById(R.id.btnLogin);
        tvCreateAccount = findViewById(R.id.tvCreateAccount);
    }

    private void addHandles() {
        btnLogin.setOnClickListener(v -> handleLogin());
        tvCreateAccount.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }

    private void handleLogin() {
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showToast("Please enter both username and password");
            return;
        }

        if (username.contains(" ")) {
            showToast("Username cannot contain spaces");
            return;
        }

        if (password.length() < 6) {
            showToast("Password must be at least 6 characters");
            return;
        }
        // Lưu tài khoản vào SharedPreferences
        getSharedPreferences("USER_PREF", MODE_PRIVATE)
                .edit()
                .putString("USERNAME", username)
                .apply();
        // Chuyển sang ProfileActivity
        Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
