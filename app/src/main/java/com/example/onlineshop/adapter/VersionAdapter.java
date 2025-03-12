package com.example.onlineshop.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.google.android.material.card.MaterialCardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.onlineshop.R;
import com.example.onlineshop.model.ProductVersion;
import java.util.List;

public class VersionAdapter extends RecyclerView.Adapter<VersionAdapter.VersionViewHolder> {
    private Context context;
    private List<ProductVersion> versionList;
    private int selectedPosition = -1;

    public VersionAdapter(Context context, List<ProductVersion> versionList) {
        this.context = context;
        this.versionList = versionList;
    }

    @NonNull
    @Override
    public VersionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_version, parent, false);
        return new VersionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VersionViewHolder holder, int position) {
        ProductVersion version = versionList.get(position);
        holder.tvConfiguration.setText(version.getConfiguration());
        holder.tvVersionPrice.setText(String.format("₫%,d", version.getPrice()));

        // Nếu phiên bản được chọn, thay đổi viền và hiển thị icon check (nếu có)
        if (position == selectedPosition) {
            holder.cardView.setStrokeColor(context.getResources().getColor(R.color.gray));
            holder.cardView.setStrokeWidth(3);
        } else {
            holder.cardView.setStrokeColor(Color.LTGRAY);
            holder.cardView.setStrokeWidth(1);
        }

        holder.itemView.setOnClickListener(v -> {
            int previousPosition = selectedPosition;
            selectedPosition = position;
            notifyItemChanged(previousPosition);
            notifyItemChanged(selectedPosition);
            // Bạn có thể thêm callback hoặc truyền dữ liệu phiên bản lên Activity nếu cần
        });
    }

    @Override
    public int getItemCount() {
        return versionList.size();
    }

    public static class VersionViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView cardView;
        TextView tvConfiguration, tvVersionPrice;

        public VersionViewHolder(@NonNull View itemView) {
            super(itemView);
            // Sử dụng MaterialCardView để hỗ trợ stroke
            cardView = (MaterialCardView) itemView;
            tvConfiguration = itemView.findViewById(R.id.tvConfiguration);
            tvVersionPrice = itemView.findViewById(R.id.tvVersionPrice);
        }
    }
}
