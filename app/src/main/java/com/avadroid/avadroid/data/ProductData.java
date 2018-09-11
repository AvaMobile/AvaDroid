package com.avadroid.avadroid.data;

import com.avadroid.avadroid.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductData {

    public static final List<Product> productList = new ArrayList<>();
    static {
        productList.add(new Product("Ice Axe", 80, 10, 0));
        productList.add(new Product("Crampons", 120, 10, 0));
        productList.add(new Product("Helmet", 40, 10, 0));
        productList.add(new Product("Boots", 400, 10, 0));
    }
}
