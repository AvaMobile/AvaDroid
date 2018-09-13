package com.avadroid.avadroid.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Product {
    public String name;
    public double taxRate;

    public Product(String name, double taxRate) {
        this.name = name;
        this.taxRate = taxRate;
    }

    @Override
    public String toString() {
        return name;
    }
}
