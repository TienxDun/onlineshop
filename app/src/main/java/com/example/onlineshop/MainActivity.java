package com.example.onlineshop;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.onlineshop.adapter.ProductAdapter;
import com.example.onlineshop.model.Product;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Product> productList;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        productList = new ArrayList<>();

        productList.add(new Product("Áo Thun Nam", R.drawable.pic1, 200000));
        productList.add(new Product("Điện Thoại", R.drawable.logo, 15000000));
        productList.add(new Product("Vé Máy Bay", R.drawable.pic5, 3000000));

        productAdapter = new ProductAdapter(this, productList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(productAdapter);
    }
}
