package com.adam.android.scan;



import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class locations extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView scannerView;
    private TextView txtResult;
    private String email2;
    private Button scan;
    private String idHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);

        idHolder = getIntent().getStringExtra("loc");
        scannerView = findViewById(R.id.zxscan);
        txtResult = findViewById(R.id.txt_result);
        scan = findViewById(R.id.btnscanned);

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email2 = txtResult.getText().toString();
                Intent i=new Intent(locations.this, confirmation.class);
                i.putExtra("email",email2);
                i.putExtra("locc",idHolder);


                startActivity(i);
            }
        });

        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        scannerView.setResultHandler(locations.this);
                        scannerView.startCamera();

                    }


                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(locations.this, "You must accept this permission", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                })
                .check();

    }

    @Override
    protected void onDestroy() {
        scannerView.stopCamera();

        super.onDestroy();
    }

    @Override
    public void handleResult(Result rawResult) {

        txtResult.setText(rawResult.getText());

    }



}