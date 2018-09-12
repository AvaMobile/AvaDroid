package com.avadroid.avadroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReviewInvoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_invoice);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.cancel_invoice)
    public void cancelTransaction() {
        finish();
    }
}
