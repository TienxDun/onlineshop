package com.example.onlineshop;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.onlineshop.model.Product;

public class DetailActivity extends AppCompatActivity {
    private ImageView imgProduct;
    private TextView txtName, txtPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgProduct = findViewById(R.id.imgProduct);
        txtName = findViewById(R.id.txtName);
        txtPrice = findViewById(R.id.txtPrice);

        Product product = (Product) getIntent().getSerializableExtra("product");

        if (product != null) {
            imgProduct.setImageResource(product.getImage());
            txtName.setText(product.getName());
            txtPrice.setText("$" + product.getPrice());
        }
    }
}
