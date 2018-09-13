package com.avadroid.avadroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.avadroid.avadroid.model.Product;
import com.avadroid.avadroid.product_data.DataObject;
import com.avadroid.avadroid.product_data.ProductData;

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


    public DataObject PRODUCTS;
    public HashMap<String, String> productMap;

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

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                PRODUCTS.PRODUCTS);

        mProductSearchList.setAdapter(adapter);
    }

    @OnClick(R.id.submit_new_transaction_btn)
    public void submitButtonHandler() {
        productKey = mProductSearchList.getText().toString();

        if (productMap.containsKey(productKey)) {
            productQuery = productMap.get(productKey);
        }

        addressQuery = mZip.getText().toString();

        Intent intent = new Intent(TaxInformationActivity.this, TaxInfoReview.class);
        intent.putExtra("ITEM", productKey);
        intent.putExtra("REGION", addressQuery);
        intent.putExtra("PRODUCT", productQuery);

        startActivity(intent);
    }

    private void populateProductList() {
        PRODUCTS = ProductData.parseProductCodes();
        productMap = PRODUCTS.productMap;
    }
}
