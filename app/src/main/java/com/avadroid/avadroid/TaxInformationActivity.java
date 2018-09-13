package com.avadroid.avadroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListAdapter;

import com.avadroid.avadroid.model.Product;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaxInformationActivity extends AppCompatActivity {
    @BindView(R.id.autocomplete_product_search) public AutoCompleteTextView mProductSearchList;

    public static final List<Product> PRODUCTS = new ArrayList<>();
    static {
        PRODUCTS.add(new Product("Medical Supplies", 0.09));
        PRODUCTS.add(new Product("Medicine", 0.09));
        PRODUCTS.add(new Product("Car", 0.09));
        PRODUCTS.add(new Product("Food", 0.09));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tax_information);

        ButterKnife.bind(this);

        ArrayAdapter<Product> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                PRODUCTS);

        mProductSearchList.setAdapter(adapter);
    }

    private void populateProductList() {
        // Where we will ping the API and pull back product data
    }
}
