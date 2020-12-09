package com.adam.android.mylogin;


import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.WriterException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class srsc extends AppCompatActivity {


    ImageView qrimg;
    TextView tvSRSC;
    TextView tvspend;
    TextView tvtracker;


    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    TextView tvemail;
    String strSRSC;
    String TAG;
    TextView tverror;
    ImageView image_view_SRSC;
    Button btnCheckin;
    String server_url_insert = "https://cgi.sice.indiana.edu/~afrankow/srsc.php";
    String server_url_insert1 = "https://cgi.sice.indiana.edu/~afrankow/srscDescrip.php";
    String server_url_insert2 = "https://cgi.sice.indiana.edu/~afrankow/srsctracker.php";
    TextView tvresult;
    TextView tvdescription;
    TextView tvdescription1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_srsc);

        btnCheckin = findViewById(R.id.btnCheckin);
        tvemail = findViewById(R.id.tvemail);
        qrimg = findViewById(R.id.qrcode);
        image_view_SRSC = findViewById(R.id.image_view_SRSC);
        TAG = "GenerateQRCode";
        tvSRSC = findViewById(R.id.tvSRSC);
        tvspend = findViewById(R.id.tvspend);
        tvresult = findViewById(R.id.tvResult);
        tvdescription = findViewById(R.id.tvdescription);
        tvdescription1 = findViewById(R.id.tvdescription1);
        tvtracker = findViewById(R.id.tvtracker);


        btnCheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvemail.setText(getIntent().getStringExtra("email"));
                strSRSC = tvemail.getText().toString().trim();
                if (strSRSC.length() > 0) {
                    WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                    Display display = manager.getDefaultDisplay();
                    Point point = new Point();
                    display.getSize(point);
                    int width = point.x;
                    int height = point.y;
                    int smallerdimension = width < height ? width : height;
                    smallerdimension = smallerdimension * 3 / 4;
                    qrgEncoder = new QRGEncoder(strSRSC, null, QRGContents.Type.TEXT, smallerdimension);
                    try {
                        bitmap = qrgEncoder.encodeAsBitmap();
                        qrimg.setImageBitmap(bitmap);
                    } catch (WriterException e) {

                        Log.v(TAG, e.toString());
                    }
                } else {
                    tverror.setError("Required");
                }


            }
        });

        if (tvspend.length() > 0) {
            try {
                Add();

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        if (tvSRSC.length() > 0) {
            try {
                Add1();

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }


        if (tvdescription1.length() > 0) {
            try {
                Add2();

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }




    }


    private void Add() throws UnsupportedEncodingException {
        String name1 = URLEncoder.encode(tvSRSC.getText().toString(), "UTF8");


        String url = server_url_insert + "?pro_name=" + name1 + "";
        Log.e("URL", url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    tvresult.setText(jsonObject.getString("message"));


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(srsc.this, "e" + e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(srsc.this, "err" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(srsc.this);
        requestQueue.add(stringRequest);


    }

    private void Add1() throws UnsupportedEncodingException {
        String name1 = URLEncoder.encode(tvSRSC.getText().toString(), "UTF8");


        String url = server_url_insert1 + "?pro_name=" + name1 + "";
        Log.e("URL", url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    tvdescription.setText(jsonObject.getString("message1"));


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(srsc.this, "e" + e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(srsc.this, "err" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(srsc.this);
        requestQueue.add(stringRequest);

    }

    private void Add2() throws UnsupportedEncodingException {
        String name1 = URLEncoder.encode(tvSRSC.getText().toString(), "UTF8");


        String url = server_url_insert2 + "?pro_name=" + name1 + "";
        Log.e("URL", url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    tvtracker.setText(jsonObject.getString("message2"));


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(srsc.this, "e" + e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(srsc.this, "err" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(srsc.this);
        requestQueue.add(stringRequest);

    }







}













