package com.example.onlineshop.fragments;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.onlineshop.ChatSupportActivity;
import com.example.onlineshop.InfoActivity;
import com.example.onlineshop.LoginActivity;
import com.example.onlineshop.R;

import java.io.IOException;

public class ProfileFragment extends Fragment {

    private ImageView imgProfile, btnEditProfile;
    private LinearLayout layoutLogout, layoutNotifications, layoutFAQ, layoutAppInfo;
    private TextView tvProfileName;

    private ActivityResultLauncher<Intent> imagePickerLauncher;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        addControls(view);
        handleEvents();

        return view;
    }

    private void addControls(View view) {
        imgProfile = view.findViewById(R.id.imgProfile);
        btnEditProfile = view.findViewById(R.id.btnEditProfile);
        layoutLogout = view.findViewById(R.id.layoutLogout);
        tvProfileName = view.findViewById(R.id.tvProfileName);
        layoutNotifications = view.findViewById(R.id.layoutNotifications);
        layoutFAQ = view.findViewById(R.id.layoutFAQ);
        layoutAppInfo = view.findViewById(R.id.layoutAppInfo);

        // Lấy dữ liệu username từ SharedPreferences (do không thể nhận intent từ Activity)
        String username = requireActivity()
                .getSharedPreferences("USER_PREF", requireActivity().MODE_PRIVATE)
                .getString("USERNAME", "User");
        tvProfileName.setText(username);

        // Đăng ký image picker
        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == getActivity().RESULT_OK && result.getData() != null) {
                        Uri imageUri = result.getData().getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), imageUri);
                            imgProfile.setImageBitmap(getCircularBitmap(bitmap));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    private void handleEvents() {
        layoutAppInfo.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InfoActivity.class);
            startActivity(intent);
        });

        layoutFAQ.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ChatSupportActivity.class);
            startActivity(intent);
        });

        layoutNotifications.setOnClickListener(v -> {
            NotificationsFragment notificationsFragment = new NotificationsFragment();
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, notificationsFragment) // Thay thế fragment
                    .addToBackStack(null) // Cho phép quay lại ProfileFragment khi nhấn back
                    .commit();
        });

        layoutLogout.setOnClickListener(v -> showLogoutDialog());

        btnEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            imagePickerLauncher.launch(intent);
        });
    }

    private void showLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setMessage("Bạn chắc chắn muốn đăng xuất?")
                .setPositiveButton("ĐỒNG Ý", (dialog, which) -> {
                    // Xóa dữ liệu đăng nhập
                    requireActivity().getSharedPreferences("USER_PREF", requireActivity().MODE_PRIVATE)
                            .edit()
                            .remove("USERNAME")
                            .apply();

                    // Chuyển về màn hình đăng nhập
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    requireActivity().finish();
                })
                .setNegativeButton("KHÔNG", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();

        Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        negativeButton.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.darker_gray));
        positiveButton.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.holo_red_dark));
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
