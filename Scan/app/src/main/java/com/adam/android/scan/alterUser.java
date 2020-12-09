package com.adam.android.scan;

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

public class alterUser extends AppCompatActivity {

    EditText etlocname;
    EditText etnewdescrip;
    Button btnAltDescrip;
    String server_url_insert = "https://cgi.sice.indiana.edu/~afrankow/cusAlter.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alteruser);

        etlocname = findViewById(R.id.etID);
        etnewdescrip = findViewById(R.id.etnewEmail);
        btnAltDescrip = findViewById(R.id.btnAltCustomer);


        btnAltDescrip.setOnClickListener(new View.OnClickListener() {
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

        String etlocname1 = URLEncoder.encode(etlocname.getText().toString(), "UTF8");
        String etnewdescrip1 = URLEncoder.encode(etnewdescrip.getText().toString(), "UTF8");



        String url = server_url_insert + "?pro_etidcus1=" + etlocname1 + "&pro_etnamecus1=" + etnewdescrip1 +  "";
        Log.e("URL", url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(alterUser.this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(alterUser.this, "e" + e.toString(), Toast.LENGTH_LONG).show();
                }

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(alterUser.this, "err" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(alterUser.this);
        requestQueue.add(stringRequest);


    }





}

