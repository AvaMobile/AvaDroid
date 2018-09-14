package com.avadroid.avadroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaxInfoReview extends AppCompatActivity {
    String taxItem;
    String isTaxable;
    String taxRegion;
    String taxCode;
    String taxRate;
    String url;

    float taxRateConversion;

    RequestQueue mRequestQueue;

    @BindView(R.id.tax_region_msg) public TextView mTaxRegion;
    @BindView(R.id.tax_code_msg) public TextView mTaxCodeMsg;
    @BindView(R.id.tax_rate_view) public TextView mTaxRateView;
    @BindView(R.id.is_taxable_item) public TextView mTaxableItem;
    @BindView(R.id.taxable_yes_no) public TextView mYesNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tax_info_review);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        taxItem = intent.getStringExtra("ITEM");
        taxRegion = intent.getStringExtra("REGION");
        taxCode = intent.getStringExtra("PRODUCT");
    }

    @Override
    protected void onStart() {
        super.onStart();
        taxRateApiCall();
    }

    public void setTextViews() {

        if (taxRate.length() > 5) {
            taxRate = taxRate.substring(0, 5);
        }

        taxRateConversion = Float.parseFloat(taxRate.substring(0, 5));
        taxRateConversion = taxRateConversion * 100;

        mTaxableItem.setText(taxItem);
        mYesNo.setText(isTaxable);
        mTaxRegion.setText(taxRegion);
        mTaxCodeMsg.setText(taxCode);
        mTaxRateView.setText("" + taxRateConversion + "%");
    }

    public void taxRateApiCall() {
        url = "https://avaserver.herokuapp.com/create/?taxCode=" + taxCode + "&zipCode=" + taxRegion;

        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
        Network network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache, network);

        mRequestQueue.start();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("GET", "get request");

                taxRate = response.split("!!")[1];
                isTaxable = response.split("!!")[0];

                setTextViews();
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
        });
        mRequestQueue.add(stringRequest);
    }

    @OnClick(R.id.go_back_btn)
    public void exitActivity() {
        finish();
    }
}
