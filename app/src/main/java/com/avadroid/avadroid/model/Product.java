package com.avadroid.avadroid.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
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

    private Product(Parcel in) {
        name = in.readString();
        price = in.readFloat();
        taxRate = in.readInt();
        quantity = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeFloat(price);
        parcel.writeInt(taxRate);
        parcel.writeInt(quantity);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
