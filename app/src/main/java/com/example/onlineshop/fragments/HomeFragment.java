package com.example.onlineshop.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.onlineshop.ItemDetailActivity;
import com.example.onlineshop.R;
import com.example.onlineshop.adapter.BannerAdapter;
import com.example.onlineshop.adapter.CategoryAdapter;
import com.example.onlineshop.adapter.ProductAdapter;
import com.example.onlineshop.model.Category;
import com.example.onlineshop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private ViewPager2 viewPagerBanner;
    private RecyclerView recyclerPopular, recyclerRecommended, recyclerCategory;
    private ProductAdapter popularAdapter, recommendedAdapter;
    private CategoryAdapter categoryAdapter;
    private List<Product> popularList, recommendedList;
    private List<Category> categoryList;
    private List<Integer> bannerList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Ánh xạ View
        viewPagerBanner = view.findViewById(R.id.viewPagerBanner);
        recyclerPopular = view.findViewById(R.id.recyclerPopular);
        recyclerRecommended = view.findViewById(R.id.recyclerRecommended);
        recyclerCategory = view.findViewById(R.id.recyclerCategory);

        // Cấu hình RecyclerView cho danh mục sản phẩm (cuộn ngang)
        recyclerCategory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        loadCategory();

        // Cấu hình RecyclerView cho sản phẩm phổ biến (cuộn ngang)
        LinearLayoutManager popularLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerPopular.setLayoutManager(popularLayoutManager);
        recyclerPopular.setNestedScrollingEnabled(true);

        // Cấu hình RecyclerView cho sản phẩm đề xuất (2 cột)
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerRecommended.setLayoutManager(gridLayoutManager);
        recyclerRecommended.setNestedScrollingEnabled(false);

        // Load danh sách sản phẩm
        loadPopularProducts();
        loadRecommendedProducts();

        // Gán Adapter cho Popular và Recommended
        popularAdapter = new ProductAdapter(getContext(), popularList);
        recyclerPopular.setAdapter(popularAdapter);

        recommendedAdapter = new ProductAdapter(getContext(), recommendedList);
        recyclerRecommended.setAdapter(recommendedAdapter);

        // Load banner
        loadBanner();
        BannerAdapter bannerAdapter = new BannerAdapter(getContext(), bannerList);
        viewPagerBanner.setAdapter(bannerAdapter);


        return view;
    }

    // Load danh sách danh mục sản phẩm
    private void loadCategory() {
        categoryList = new ArrayList<>();
        categoryList.add(new Category("Laptop", R.drawable.ic_laptop));
        categoryList.add(new Category("Điện thoại", R.drawable.ic_phone));
        categoryList.add(new Category("Đồng hồ", R.drawable.ic_watch));
        categoryList.add(new Category("Âm thanh", R.drawable.ic_audio));
        categoryList.add(new Category("Đồ gia dụng", R.drawable.ic_home_appliances));
        categoryList.add(new Category("Phụ kiện", R.drawable.ic_accessories));
        categoryList.add(new Category("Màn hình", R.drawable.ic_monitor));
        categoryList.add(new Category("Tivi", R.drawable.ic_tv));

        categoryAdapter = new CategoryAdapter(categoryList);
        recyclerCategory.setAdapter(categoryAdapter);
    }

    // Load danh sách sản phẩm phổ biến
    private void loadPopularProducts() {
        popularList = new ArrayList<>();
        popularList.add(new Product("Loa Bluetooth A88 Pro", R.drawable.pic1, 206000, 0.41, 5.0f, "TP. Hồ Chí Minh"));
        popularList.add(new Product("Tai nghe Sony WH-1000XM4", R.drawable.pic2, 5990000, 0.15, 4.8f, "Hà Nội"));
        popularList.add(new Product("Điện thoại iPhone 15 Pro", R.drawable.pic3, 29990000, 0.1, 4.9f, "Đà Nẵng"));
        popularList.add(new Product("Máy tính MacBook Pro M3", R.drawable.pic5, 34990000, 0.05, 4.7f, "Hồ Chí Minh"));
        popularList.add(new Product("Chuột Logitech MX Master 3S", R.drawable.pic6, 2490000, 0.2, 4.6f, "Cần Thơ"));
        popularList.add(new Product("Bàn phím cơ Keychron K6", R.drawable.pic1, 1890000, 0.3, 4.7f, "Hà Nội"));
    }

    // Load danh sách sản phẩm đề xuất
    private void loadRecommendedProducts() {
        recommendedList = new ArrayList<>();
        recommendedList.add(new Product("Màn hình Dell UltraSharp 27\"", R.drawable.pic2, 8990000, 0.1, 4.8f, "Hồ Chí Minh"));
        recommendedList.add(new Product("Ổ cứng SSD Samsung 1TB", R.drawable.pic3, 3290000, 0.2, 4.7f, "Đà Nẵng"));
        recommendedList.add(new Product("Loa JBL Charge 5", R.drawable.pic1, 2990000, 0.15, 4.9f, "Hà Nội"));
        recommendedList.add(new Product("Bàn phím cơ Razer Huntsman Mini", R.drawable.pic5, 2690000, 0.2, 4.5f, "TP. Hồ Chí Minh"));
        recommendedList.add(new Product("Màn hình Dell UltraSharp 27\"", R.drawable.pic2, 8990000, 0.1, 4.8f, "Hồ Chí Minh"));
        recommendedList.add(new Product("Ổ cứng SSD Samsung 1TB", R.drawable.pic3, 3290000, 0.2, 4.7f, "Đà Nẵng"));
        recommendedList.add(new Product("Loa JBL Charge 5", R.drawable.pic1, 2990000, 0.15, 4.9f, "Hà Nội"));
        recommendedList.add(new Product("Bàn phím cơ Razer Huntsman Mini", R.drawable.pic5, 2690000, 0.2, 4.5f, "TP. Hồ Chí Minh"));
    }

    // Load danh sách banner
    private void loadBanner() {
        bannerList = new ArrayList<>();
        bannerList.add(R.drawable.banner1);
        bannerList.add(R.drawable.banner2);
        bannerList.add(R.drawable.banner3);
        bannerList.add(R.drawable.banner4);
        bannerList.add(R.drawable.banner5);
        bannerList.add(R.drawable.banner6);
        bannerList.add(R.drawable.banner7);
    }
}
