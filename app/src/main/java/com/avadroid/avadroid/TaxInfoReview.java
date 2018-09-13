package com.avadroid.avadroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaxInfoReview extends AppCompatActivity {
    String taxRegion;
    String taxCode;

    @BindView(R.id.tax_region_msg) public TextView mTaxRegion;
    @BindView(R.id.tax_code_msg) public TextView mTaxCodeMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tax_info_review);

        ButterKnife.bind(this);

        setTextViews();
    }

    public void setTextViews() {
        Intent intent = getIntent();

        taxRegion = intent.getStringExtra("REGION");
        taxCode = intent.getStringExtra("PRODUCT");

        mTaxRegion.setText(taxRegion);
        mTaxCodeMsg.setText(taxCode);
    }
}
