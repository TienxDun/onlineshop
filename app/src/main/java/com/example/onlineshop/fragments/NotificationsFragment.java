package com.example.onlineshop.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.NotificationAdapter;
import com.example.onlineshop.model.Notification;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {
    RecyclerView recyclerNotifications;
    ArrayList<Notification> notificationList;
    NotificationAdapter adapter;

    public NotificationsFragment() {
        // Constructor rỗng
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        addControls(view);
        handleEvents();
        return view;
    }

    private void addControls(View view) {
        recyclerNotifications = view.findViewById(R.id.recyclerNotifications);
    }

    private void handleEvents() {
        notificationList = new ArrayList<>();

        // Thêm dữ liệu thông báo mẫu
        notificationList.add(new Notification("Thông báo", "Bạn có một tin nhắn mới!"));
        notificationList.add(new Notification("Khuyến mãi", "Giảm giá 50% khi mua hàng hôm nay!"));
        notificationList.add(new Notification("Cập nhật", "Ứng dụng đã được cập nhật lên phiên bản mới."));

        adapter = new NotificationAdapter(notificationList);
        recyclerNotifications.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerNotifications.setAdapter(adapter);
    }
}
