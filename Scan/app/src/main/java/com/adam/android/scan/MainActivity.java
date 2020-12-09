package com.adam.android.scan;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;

import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class MainActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btnlogin;
    private Button btnProceed;
    private TextView tvError;
    private TextView locID;
    private String locationid;
    private Boolean locCheck;
    private Boolean admCheck;



    String server_url_insert = "https://cgi.sice.indiana.edu/~afrankow/staffcheck.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnlogin = findViewById(R.id.btnlogin);
        btnProceed = findViewById(R.id.btnProceed);
        tvError = findViewById(R.id.tvError);
        locID = findViewById(R.id.locID);







        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    Add();

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


                locCheck = etUsername.getText().toString().contains("1");

            }


        });

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if (locCheck) {
                String locID1 = "One";
                locID.setText(locID1);
                Intent i = new Intent(MainActivity.this, locations.class);
                i.putExtra("loc",locID1);
                startActivity(i);
            }

            else if (admCheck) {
                Intent i = new Intent(MainActivity.this, admin.class);
                startActivity(i);
            }


            }
        });


    }




    private void Add() throws UnsupportedEncodingException {

        locCheck = etUsername.getText().toString().contains("1");
        admCheck = etUsername.getText().toString().contains("9");





        String password1 = URLEncoder.encode(etPassword.getText().toString(), "UTF8");
        String email1 = URLEncoder.encode(etUsername.getText().toString(), "UTF8");


        String url = server_url_insert + "?pro_password=" + password1 + "&pro_email=" + email1 + "";
        Log.e("URL", url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(MainActivity.this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                    tvError.setText(jsonObject.getString("message"));

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "e" + e.toString(), Toast.LENGTH_LONG).show();
                }

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "err" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);


    }

}









