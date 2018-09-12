package com.avadroid.avadroid.model;

public class Product {
    public String name;
    public float price;
    public int taxRate;
    public int quantity;

    public Product(String name, float price, int taxRate, int quantity) {
        this.name = name;
        this.price = price;
        this.taxRate = taxRate;
        this.quantity = quantity;
    }
}
