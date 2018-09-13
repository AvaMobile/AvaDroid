package com.avadroid.avadroid.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Product {
    public String name;
    public String taxCode;

    public Product(String name, String taxCode) {
        this.name = name;
        this.taxCode = taxCode;
    }

    @Override
    public String toString() {
        return name;
    }
}
