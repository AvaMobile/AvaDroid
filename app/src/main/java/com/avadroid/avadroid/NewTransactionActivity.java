package com.avadroid.avadroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.avadroid.avadroid.adaptor.Adapter;
import com.avadroid.avadroid.data.ProductData;
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
    @BindView(R.id.add_product_quantity) public TextView mQuantity;

    public List<Product> newInvoice = new ArrayList<>();

    public int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_transaction);

        ButterKnife.bind(this);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new Adapter(newInvoice);
        mRecyclerView.setAdapter(mAdapter);
    }

    @OnClick(R.id.increment_quantity)
    public void incrementQuantity() {
        quantity++;
        mQuantity.setText("" + quantity);
    }

    @OnClick(R.id.decrement_quantity)
    public void decrementQuantity() {
        if (quantity == 0) {
            return;
        } else {
            quantity--;
            mQuantity.setText("" + quantity);
        }
    }
}
