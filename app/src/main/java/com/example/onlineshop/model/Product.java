package com.example.onlineshop.model;

public class Product {
    private String name;
    private int imageResId;  // Dùng int thay vì String
    private double price;
    private double discount;
    private float rating;
    private String location;

    public Product(String name, int imageResId, double price, double discount, float rating, String location) {
        this.name = name;
        this.imageResId = imageResId;
        this.price = price;
        this.discount = discount;
        this.rating = rating;
        this.location = location;
    }

    public String getName() { return name; }
    public int getImageResId() { return imageResId; } // Trả về int thay vì String
    public double getPrice() { return price; }
    public double getDiscount() { return discount; }
    public float getRating() { return rating; }
    public String getLocation() { return location; }
}

