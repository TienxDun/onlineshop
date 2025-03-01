package com.example.onlineshop;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.adapter.NotificationAdapter;
import com.example.onlineshop.model.Notification;

import java.util.ArrayList;

public class NotificationsActivity extends AppCompatActivity {
    RecyclerView recyclerNotifications;
    ArrayList<Notification> notificationList;
    NotificationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        addControls();
        handleEvents();
    }

    private void handleEvents() {
        notificationList = new ArrayList<>();

        // Thêm dữ liệu thông báo mẫu
        notificationList.add(new Notification("Khuyến mãi", "Giảm giá 50% khi mua hàng hôm nay!"));
        notificationList.add(new Notification("Cập nhật", "Ứng dụng đã được cập nhật lên phiên bản mới."));

        adapter = new NotificationAdapter(notificationList);
        recyclerNotifications.setLayoutManager(new LinearLayoutManager(this));
        recyclerNotifications.setAdapter(adapter);
    }

    private void addControls() {
        recyclerNotifications = findViewById(R.id.recyclerNotifications);

    }
}
