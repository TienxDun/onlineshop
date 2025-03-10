package com.example.onlineshop.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.BannerAdapter;
import com.example.onlineshop.adapter.ProductAdapter;
import com.example.onlineshop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private ViewPager2 viewPagerBanner;
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private List<Integer> bannerList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewPagerBanner = view.findViewById(R.id.viewPagerBanner);
        recyclerView = view.findViewById(R.id.recyclerPopular);

        // Cấu hình RecyclerView
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // Load sản phẩm
        loadProducts();
        productAdapter = new ProductAdapter(getContext(), productList);
        recyclerView.setAdapter(productAdapter);

        // Load banner
        loadBanner();
        BannerAdapter bannerAdapter = new BannerAdapter(getContext(), bannerList);
        viewPagerBanner.setAdapter(bannerAdapter);

        return view;
    }

    private void loadProducts() {
        productList = new ArrayList<>();
        productList.add(new Product("Loa Bluetooth A88 Pro", R.drawable.pic1, 206000, 0.41, 5.0f, "TP. Hồ Chí Minh"));
        productList.add(new Product("Tai nghe Sony WH-1000XM4", R.drawable.pic2, 5990000, 0.15, 4.8f, "Hà Nội"));
        productList.add(new Product("Điện thoại iPhone 15 Pro", R.drawable.pic3, 29990000, 0.1, 4.9f, "Đà Nẵng"));
        productList.add(new Product("Máy tính MacBook Pro M3", R.drawable.pic5, 34990000, 0.05, 4.7f, "Hồ Chí Minh"));
    }

    private void loadBanner() {
        bannerList = new ArrayList<>();
        bannerList.add(R.drawable.pic1);
        bannerList.add(R.drawable.pic2);
        bannerList.add(R.drawable.pic3);
    }
}
