package com.avadroid.avadroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.avadroid.avadroid.adaptor.Adapter;
import com.avadroid.avadroid.data.ProductData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewTransactionActivity extends AppCompatActivity {
    private LinearLayoutManager mLayoutManager;
    private Adapter mAdapter;

    @BindView(R.id.product_invoice_recycler) public RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_transaction);

        ButterKnife.bind(this);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new Adapter(ProductData.productList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
