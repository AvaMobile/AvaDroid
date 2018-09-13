package com.avadroid.avadroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class TaxInfoReview extends AppCompatActivity {
    String taxRegion;
    String taxCode;
    String taxRate;
    String url;

    RequestQueue mRequestQueue;

    @BindView(R.id.tax_region_msg) public TextView mTaxRegion;
    @BindView(R.id.tax_code_msg) public TextView mTaxCodeMsg;
    @BindView(R.id.tax_rate_view) public TextView mTaxRateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tax_info_review);

        ButterKnife.bind(this);

        taxRateApiCall();
    }

    public void setTextViews() {
        Intent intent = getIntent();

        taxRegion = intent.getStringExtra("REGION");
        taxCode = intent.getStringExtra("PRODUCT");

        mTaxRegion.setText(taxRegion);
        mTaxCodeMsg.setText(taxCode);
        mTaxRateView.setText("" + taxRate);
    }

    public void taxRateApiCall() {
        // get?param=" + taxCode
        url = "https://avaserver.herokuapp.com/create";

        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
        Network network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache, network);

        mRequestQueue.start();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                taxRate = response;
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
}
