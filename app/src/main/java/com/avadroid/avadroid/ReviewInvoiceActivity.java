package com.avadroid.avadroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.avadroid.avadroid.adapter.Adapter;
import com.avadroid.avadroid.model.Product;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReviewInvoiceActivity extends AppCompatActivity {
    private LinearLayoutManager mLayoutManager;
    private Adapter mAdapter;

    @BindView(R.id.review_invoice_recycler) public RecyclerView mRecyclerView;

    ArrayList<Product> reviewNewInvoice;

    int totalBeforeTax;
    int totalTaxAmount;
    int totalWithTax;

    float taxRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_invoice);

        ButterKnife.bind(this);

        reviewNewInvoice = getIntent().getParcelableArrayListExtra("TRANSACTION");

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new Adapter(reviewNewInvoice);
        mRecyclerView.setAdapter(mAdapter);
    }

    @OnClick(R.id.cancel_invoice)
    public void cancelTransaction() {
        finish();
    }

    public void getTaxRate() {
        // Pull tax rate back from API based on address
    }

    public void calculateTotals() {
        for (Product product : reviewNewInvoice) {
            totalBeforeTax += (product.price * product.quantity);
        }
    }
}
