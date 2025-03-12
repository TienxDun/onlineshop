package com.example.onlineshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.ItemDetailActivity;
import com.example.onlineshop.R;
import com.example.onlineshop.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.txtName.setText(product.getName());
        holder.txtPrice.setText(String.format("₫%,.0f", product.getPrice()));
        holder.txtDiscount.setText(String.format("-%.0f%%", product.getDiscount() * 100));
        holder.txtLocation.setText(product.getLocation());

        holder.imgProduct.setImageResource(product.getImageResId());
        //Sự kiện click vào sản phẩm
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ItemDetailActivity.class);
            intent.putExtra("productName", product.getName());
            intent.putExtra("productImage", product.getImageResId());
            intent.putExtra("productPrice", product.getPrice());
            double oldPrice = product.getPrice() / (1 - product.getDiscount()); // ví dụ tính giá cũ
            intent.putExtra("productOldPrice", oldPrice);
            intent.putExtra("productDescription", "Mô tả chi tiết sản phẩm ở đây...");
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtPrice, txtDiscount, txtLocation;
        ImageView imgProduct;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.tvProductName);
            txtPrice = itemView.findViewById(R.id.tvProductPrice);
            txtDiscount = itemView.findViewById(R.id.tvProductDiscount);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            txtLocation = itemView.findViewById(R.id.tvProductLocation);
        }
    }
}
