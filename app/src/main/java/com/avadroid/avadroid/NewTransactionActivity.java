package com.avadroid.avadroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import com.avadroid.avadroid.adapter.Adapter;
import com.avadroid.avadroid.model.Product;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewTransactionActivity extends AppCompatActivity {
    private LinearLayoutManager mLayoutManager;
    private Adapter mAdapter;

    @BindView(R.id.product_invoice_recycler) public RecyclerView mRecyclerView;
    @BindView(R.id.searchView) public SearchView mSearchView;
    @BindView(R.id.add_product_quantity) public EditText mQuantity;
    @BindView(R.id.add_product_price) public EditText mPrice;

    public ArrayList<Product> newInvoice = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_transaction);

        ButterKnife.bind(this);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new Adapter(newInvoice);
        mRecyclerView.setAdapter(mAdapter);

        mSearchView.setIconified(false);
    }

    @OnClick(R.id.add_product_to_invoice_btn)
    public void addProductToNewInvoice() {
        String name = mSearchView.getQuery().toString();

        String tempQuantity = mQuantity.getText().toString();
        int quantity = Integer.parseInt(tempQuantity);

        String tempPrice = mPrice.getText().toString();
        float price = Float.parseFloat(tempPrice);


        newInvoice.add(new Product(name, price, 10, quantity));

        mSearchView.setQuery("", false);
        mSearchView.clearFocus();

        mQuantity.setText("");
        mPrice.setText("");

        mAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.submit_new_invoice_btn)
    public void submitNewTransaction() {
        Intent intent = new Intent(NewTransactionActivity.this, ReviewInvoiceActivity.class);
        intent.putParcelableArrayListExtra("TRANSACTION", newInvoice);

        startActivity(intent);
    }
}
