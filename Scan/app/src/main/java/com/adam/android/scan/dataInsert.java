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

public class dataInsert extends AppCompatActivity {

    Button btnDelete;
    Button btnAlter;
    EditText etDelete;
    EditText etlocID;
    EditText etName;
    EditText etDescrip;
    EditText etEmail1;
    EditText etPassword;
    Button btnInsert;
    String server_url_insert = "https://cgi.sice.indiana.edu/~afrankow/locDataInsert.php";
    String server_url_insert1 = "https://cgi.sice.indiana.edu/~afrankow/locDelete.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datainsert);

        etlocID = findViewById(R.id.etlocID);
        etName = findViewById(R.id.etName);
        etDescrip = findViewById(R.id.etDescrip);
        etEmail1 = findViewById(R.id.etEmail1);
        etPassword = findViewById(R.id.etPassword);
        btnInsert = findViewById(R.id.btnInsert);
        btnDelete = findViewById(R.id.btnDelete);
        etDelete = findViewById(R.id.etDelete);
        btnAlter = findViewById(R.id.btnAlter);

        btnAlter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dataInsert.this, alterlocations.class);
                startActivity(i);
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Add();

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Add1();

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
        }

        );


    }

    private void Add() throws UnsupportedEncodingException {

        String etlocID1 = URLEncoder.encode(etlocID.getText().toString(), "UTF8");
        String etName1 = URLEncoder.encode(etName.getText().toString(), "UTF8");
        String etDescrip1 = URLEncoder.encode(etDescrip.getText().toString(), "UTF8");
        String etEmail2 = URLEncoder.encode(etEmail1.getText().toString(), "UTF8");
        String etPassword1 = URLEncoder.encode(etPassword.getText().toString(), "UTF8");

        String url = server_url_insert + "?pro_etlocID1=" + etlocID1 + "&pro_etName1=" + etName1 + "&pro_etDescrip1=" + etDescrip1 + "&pro_etEmail12=" +
                etEmail2 + "&pro_etPassword1=" + etPassword1 + "";
        Log.e("URL", url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(dataInsert.this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(dataInsert.this, "e" + e.toString(), Toast.LENGTH_LONG).show();
                }

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(dataInsert.this, "err" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(dataInsert.this);
        requestQueue.add(stringRequest);


    }
    private void Add1() throws UnsupportedEncodingException {

        String etlocID1 = URLEncoder.encode(etDelete.getText().toString(), "UTF8");


        String url = server_url_insert1 + "?pro_etlocID1=" + etlocID1 + "";
        Log.e("URL", url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(dataInsert.this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(dataInsert.this, "e" + e.toString(), Toast.LENGTH_LONG).show();
                }

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(dataInsert.this, "err" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(dataInsert.this);
        requestQueue.add(stringRequest);


    }

}


