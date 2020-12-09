package com.adam.android.mylogin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class locations extends AppCompatActivity {

    ImageView qrimg;

    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    TextView tvemail;
    String strSRSC;
    String TAG;
    TextView tverror;
    ImageView image_view_SRSC;
    Button buttonSRSC;
    Button buttonKroger;
    Button btnWic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);
        qrimg = findViewById(R.id.qrcode);
        tvemail = findViewById(R.id.tvemail);
        image_view_SRSC = findViewById(R.id.image_view_SRSC);
        buttonSRSC = findViewById(R.id.btnSRSC);
        TAG = "GenerateQRCode";
        buttonKroger = findViewById(R.id.buttonKroger);
        btnWic = findViewById(R.id.btnWic);


        buttonSRSC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvemail.setText(getIntent().getStringExtra("email"));
                String email2 = tvemail.getText().toString();
                Intent i=new Intent(locations.this, srsc.class);
                i.putExtra("email",email2);

                startActivity(i);}});




        buttonKroger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvemail.setText(getIntent().getStringExtra("email"));
                String email2 = tvemail.getText().toString();
                Intent i=new Intent(locations.this, kroger.class);
                i.putExtra("email",email2);

                startActivity(i);}});

        btnWic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvemail.setText(getIntent().getStringExtra("email"));
                String email2 = tvemail.getText().toString();
                Intent i=new Intent(locations.this, wic.class);
                i.putExtra("email",email2);

                startActivity(i);}});



    }
}



