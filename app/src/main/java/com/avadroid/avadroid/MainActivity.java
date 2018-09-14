package com.avadroid.avadroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        if(!loggedIn){
            makeToast("Please login for \ntax code checks");
        }

        final Button login = findViewById(R.id.login_button);
        final EditText mUsername = findViewById(R.id.username);
        final EditText mPassword = findViewById(R.id.password);

        mPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_DONE){
                    login(mUsername.getText().toString(), mPassword.getText().toString());
                    return true;
                }
                return false;
            }
        });


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

            makeToast("Enter Username and Password");

        } else {
            RequestQueue queue = Volley.newRequestQueue(this);

            try {
                username = URLEncoder.encode(username, "UTF-8");
                password = URLEncoder.encode(password, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            String url = "https://avatax-server.herokuapp.com/authorization?username="
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

                                makeToast("You are now logged in \nGoing to search now");
                                Log.d("AUTH STATUS", String.valueOf(loggedIn));

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
        Intent intent = new Intent(this, TaxInformationActivity.class);
        startActivity(intent);
    }

    private void makeToast(String s) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast,
                (ViewGroup) findViewById(R.id.toast_container));

        TextView text = layout.findViewById(R.id.text);
        text.setText(s);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, -400);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }


}
