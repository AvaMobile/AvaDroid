package com.avadroid.avadroid.adaptor;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.avadroid.avadroid.R;
import com.avadroid.avadroid.model.Product;

import java.util.ArrayList;
import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private List<Product> mProductList;

    public Adapter() {
        mProductList = new ArrayList<>();
    }

    public Adapter(List<Product> productList) {
        mProductList = productList;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup vg, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(vg.getContext());
        View view = inflater.inflate(R.layout.recycler_invoice_item, vg, false);

        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        Product product = mProductList.get(i);
        myViewHolder.bind(product);
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public TextView mName;
        public TextView mQuantity;
        public TextView mPrice;

        private Product mProduct;

        public MyViewHolder(View view) {
            super(view);
            mView = view;

            mName = mView.findViewById(R.id.invoice_item_name);
            mQuantity = mView.findViewById(R.id.invoice_item_quantity);
            mPrice = mView.findViewById(R.id.invoice_item_price);
        }

        public void bind(Product product) {
            mProduct = product;
            mName.setText(product.name);
            mQuantity.setText("" + product.quantity);
            mPrice.setText("" + product.price);
        }
    }
}
