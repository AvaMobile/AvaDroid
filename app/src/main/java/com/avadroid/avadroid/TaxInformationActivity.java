package com.avadroid.avadroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListAdapter;

import com.avadroid.avadroid.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaxInformationActivity extends AppCompatActivity {
    public String addressQuery;
    public String productKey;
    public String productQuery;

    public static final List<Product> PRODUCTS = new ArrayList<>();

    private Map<String, String> productMap = new HashMap<>();

    @BindView(R.id.autocomplete_product_search) public AutoCompleteTextView mProductSearchList;
    @BindView(R.id.address_street) public EditText mStreet;
    @BindView(R.id.address_city) public EditText mCity;
    @BindView(R.id.address_state) public EditText mState;
    @BindView(R.id.address_zip) public EditText mZip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tax_information);

        ButterKnife.bind(this);

        populateProductList();

        ArrayAdapter<Product> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                PRODUCTS);

        mProductSearchList.setAdapter(adapter);
    }

    @OnClick(R.id.submit_new_transaction_btn)
    private void submitButtonHandler() {
        productKey = mProductSearchList.getText().toString();
        productQuery = productMap.get(productKey);

        addressQuery = mStreet + " " + mCity + ", " + mState + " " + mZip;

        Intent intent = new Intent(TaxInformationActivity.this, TaxInfoReview.class);
        intent.putExtra("PRODUCT", productQuery);
        intent.putExtra("ADDRESS", addressQuery);

        startActivity(intent);
    }

    private void populateProductList() {
        productMap = ProductData.parseProductCodes();

    }
}
