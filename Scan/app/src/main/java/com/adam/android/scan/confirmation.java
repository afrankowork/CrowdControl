package com.adam.android.scan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class confirmation extends AppCompatActivity {


    TextView tvUsername;
    TextView tvLocation;

    Button btnFinal;
    Button btnCheck;
    Button btnScanner;
    String server_url_insert = "https://cgi.sice.indiana.edu/~afrankow/userCheckin.php";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        tvUsername = findViewById(R.id.tvUsername);
        tvLocation = findViewById(R.id.tvLocation);
        tvUsername.setText(getIntent().getStringExtra("email"));
        tvLocation.setText(getIntent().getStringExtra("locc"));
        btnFinal = findViewById(R.id.btnFinal);
        btnCheck = findViewById(R.id.btnCheck);
        btnScanner = findViewById(R.id.btnScanner);



        btnScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(confirmation.this, locations.class);
                startActivity(i);

            }
        });


        btnFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    Add();

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }


        });

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Add();

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


            }
        });

    }

    private void Add() throws UnsupportedEncodingException {


        String password1 = URLEncoder.encode(tvLocation.getText().toString(), "UTF8");
        String email1 = URLEncoder.encode(tvUsername.getText().toString(), "UTF8");


        String url = server_url_insert + "?pro_password=" + password1 + "&pro_email=" + email1 + "";
        Log.e("URL", url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(confirmation.this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(confirmation.this, "e" + e.toString(), Toast.LENGTH_LONG).show();

                }

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(confirmation.this, "err" + error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(confirmation.this);
        requestQueue.add(stringRequest);


    }






}


















