package com.example.onlineshop.model;

public class ProductVersion {
    private String configuration; // Ví dụ: "8GB/128GB" hoặc "8GB/256GB"
    private long price;           // Giá của phiên bản

    public ProductVersion(String configuration, long price) {
        this.configuration = configuration;
        this.price = price;
    }

    public String getConfiguration() {
        return configuration;
    }

    public long getPrice() {
        return price;
    }
}
