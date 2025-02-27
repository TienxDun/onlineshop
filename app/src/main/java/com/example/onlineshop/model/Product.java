package com.example.onlineshop.model;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private int image;
    private double price;

    public Product(String name, int image, double price) {
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public double getPrice() {
        return price;
    }
}
