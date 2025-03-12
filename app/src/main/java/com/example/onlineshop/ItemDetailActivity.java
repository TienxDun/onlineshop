package com.example.onlineshop;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.adapter.VersionAdapter;
import com.example.onlineshop.model.ProductVersion;

import java.util.ArrayList;
import java.util.List;

public class ItemDetailActivity extends AppCompatActivity {

    private ImageView imgBack, imgShare, imgMainProduct;
    private TextView tvProductName, tvProductOldPrice, tvProductPrice, tvProductDescription;
    private Button btnAddToCart;
    private RecyclerView recyclerVersion;
    private VersionAdapter versionAdapter;
    private List<ProductVersion> versionList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        addControls();
        handleEvents();
        loadProductData();
        loadVersions();
    }

    private void addControls() {
        // Ánh xạ view
        imgBack = findViewById(R.id.imgBack);
        imgShare = findViewById(R.id.imgShare);
        imgMainProduct = findViewById(R.id.imgMainProduct);
        tvProductName = findViewById(R.id.tvProductName);
        tvProductOldPrice = findViewById(R.id.tvProductOldPrice);
        tvProductPrice = findViewById(R.id.tvProductPrice);
        tvProductDescription = findViewById(R.id.tvProductDescription);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        recyclerVersion = findViewById(R.id.recyclerVersion);
    }

    private void handleEvents() {
        // Nút Back: quay về màn hình trước đó
        imgBack.setOnClickListener(v -> finish());

        // Nút Share: chia sẻ sản phẩm
        imgShare.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this product!");
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        });

        // Nút Add to Cart: xử lý thêm sản phẩm vào giỏ hàng
        btnAddToCart.setOnClickListener(v -> {
            // Xử lý thêm sản phẩm vào giỏ hàng
        });
    }

    private void loadProductData() {
        // Nhận dữ liệu từ Intent (được truyền từ HomeFragment/ProductAdapter)
        Intent intent = getIntent();
        if (intent != null) {
            String productName = intent.getStringExtra("productName");
            int productImage = intent.getIntExtra("productImage", R.drawable.pic1); // sử dụng pic1 nếu không có
            double productPrice = intent.getDoubleExtra("productPrice", 0);
            double productOldPrice = intent.getDoubleExtra("productOldPrice", 0);
            String productDescription = intent.getStringExtra("productDescription");

            tvProductName.setText(productName != null ? productName : "Tên sản phẩm");
            imgMainProduct.setImageResource(productImage);
            tvProductPrice.setText(String.format("₫%,.0f", productPrice));
            tvProductOldPrice.setText(String.format("₫%,.0f", productOldPrice));
            // Áp dụng gạch ngang cho giá cũ:
            tvProductOldPrice.setPaintFlags(tvProductOldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            tvProductDescription.setText(productDescription != null ? productDescription : "Mô tả sản phẩm...");
        }
    }

    private void loadVersions() {
        versionList = new ArrayList<>();

        // Ví dụ dữ liệu phiên bản sản phẩm (cho điện thoại)
        versionList.add(new ProductVersion("8GB RAM/128GB", 15990000));
        versionList.add(new ProductVersion("8GB RAM/256GB", 17990000));
        // Thêm các phiên bản khác nếu cần

        versionAdapter = new VersionAdapter(this, versionList);
        recyclerVersion.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerVersion.setAdapter(versionAdapter);
    }

}
