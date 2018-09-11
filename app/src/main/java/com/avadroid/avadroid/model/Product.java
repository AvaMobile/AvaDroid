package com.avadroid.avadroid.model;

public class Product {
    public String name;
    public int price;
    public int taxRate;
    public int quantity;

    public Product(String name, int price, int taxRate, int quantity) {
        this.name = name;
        this.price = price;
        this.taxRate = taxRate;
        this.quantity = quantity;
    }
}
