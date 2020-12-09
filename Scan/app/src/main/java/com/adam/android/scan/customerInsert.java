package com.adam.android.scan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class customerInsert extends AppCompatActivity {

    EditText etidcus;
    EditText etnamecus;
    EditText cusDelete;
    Button btnalt;

    EditText etemailcus;

    Button btnInsertcus;
    Button btnDelete1;
    String server_url_insert = "https://cgi.sice.indiana.edu/~afrankow/customerInsert.php";
    String server_url_insert1 = "https://cgi.sice.indiana.edu/~afrankow/cusDelete.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerinsert);

        etidcus = findViewById(R.id.etidcus);
        etnamecus = findViewById(R.id.etnamecus);
        etemailcus= findViewById(R.id.etemailcus);
        cusDelete = findViewById(R.id.cusDelete);
        btnalt = findViewById(R.id.btnalt);

        btnInsertcus = findViewById(R.id.btnInsertcus);
        btnDelete1 = findViewById(R.id.btnDelete1);

        btnalt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(customerInsert.this, alterUser.class);
                startActivity(i);
            }
        });


        btnDelete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Add1();

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });

        btnInsertcus.setOnClickListener(new View.OnClickListener() {
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

        String etidcus1 = URLEncoder.encode(etidcus.getText().toString(), "UTF8");
        String etnamecus1 = URLEncoder.encode(etnamecus.getText().toString(), "UTF8");
        String etemailcus1 = URLEncoder.encode(etemailcus.getText().toString(), "UTF8");


        String url = server_url_insert + "?pro_etidcus1=" + etidcus1 + "&pro_etnamecus1=" + etnamecus1 + "&pro_etemailcus1=" + etemailcus1 + "";
        Log.e("URL", url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(customerInsert.this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(customerInsert.this, "e" + e.toString(), Toast.LENGTH_LONG).show();
                }

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(customerInsert.this, "err" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(customerInsert.this);
        requestQueue.add(stringRequest);


    }
    private void Add1() throws UnsupportedEncodingException {

        String etidcus1 = URLEncoder.encode(cusDelete.getText().toString(), "UTF8");



        String url = server_url_insert1 + "?pro_etidcus1=" + etidcus1 + "";
        Log.e("URL", url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(customerInsert.this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(customerInsert.this, "e" + e.toString(), Toast.LENGTH_LONG).show();
                }

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(customerInsert.this, "err" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(customerInsert.this);
        requestQueue.add(stringRequest);


    }

}