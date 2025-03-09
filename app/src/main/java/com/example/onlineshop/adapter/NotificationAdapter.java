package com.example.onlineshop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.model.Notification;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    private ArrayList<Notification> notifications;

    public NotificationAdapter(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Notification notification = notifications.get(position);
        holder.txtTitle.setText(notification.getTitle());
        holder.txtContent.setText(notification.getContent());
        // Set different icons based on notification type (optional)
        if (notification.getTitle().contains("Khuyến mãi")) {
            holder.imgIcon.setImageResource(R.drawable.ic_discount); // Example icon for discount
        } else if (notification.getTitle().contains("Cập nhật")) {
            holder.imgIcon.setImageResource(R.drawable.ic_update); // Example icon for update
        }
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtContent;
        ImageView imgIcon;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtContent = itemView.findViewById(R.id.txtContent);
            imgIcon = itemView.findViewById(R.id.imgIcon);
        }
    }
}