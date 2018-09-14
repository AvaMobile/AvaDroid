package com.avadroid.avadroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
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
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    public static boolean loggedIn = false;
    public EditText mUsername;
    public EditText mPassword;
    public String userName;
    public String passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        final Button login = findViewById(R.id.login_button);
        mUsername = findViewById(R.id.username);
        mPassword = findViewById(R.id.password);
    }
        @OnClick(R.id.login_button)
        protected void loginButtonHandler () {
            userName = mUsername.getText().toString();
            passWord = mPassword.getText().toString();
            login(userName, passWord);
        }

    @Override
    protected void onStart() {
        super.onStart();

        RequestQueue mRequestQueue;

        // get?param=" + taxCode
        String url = "https://avaserver.herokuapp.com/ping";

        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
        Network network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache, network);

        mRequestQueue.start();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               Log.d("PING", "pinged server");
            }
        },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
        mRequestQueue.add(stringRequest);
    }


    public void login(String username, String password){
        if (username == null || password == null) {

        } else {
            RequestQueue queue = Volley.newRequestQueue(this);

            try {
                username = URLEncoder.encode(username, "UTF-8");
                password = URLEncoder.encode(password, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            String url = "https://avaserver.herokuapp.com/authorization?username="
                    + username + "&password=" + password;
            Log.d("URL PASS", url);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("RESPONSE: ", response);
                            boolean authStatus = Boolean.parseBoolean(response.toString());

                            if(authStatus){

                                loggedIn = authStatus;
                                goToTaxInfoActivity();

                            } else {

                                loggedIn = false;
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            queue.add(stringRequest);
        }
    }

    private void goToTaxInfoActivity() {
        Intent intent = new Intent(MainActivity.this, TaxInformationActivity.class);
        intent.putExtra("userName", userName).putExtra("passWord", passWord);
        startActivity(intent);
    }
}
