package com.adam.android.scan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class admin extends AppCompatActivity {



    Button adminlocation;
    Button btncusedit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);

        adminlocation = findViewById(R.id.btnadminlocation);
        btncusedit = findViewById(R.id.btncusedit);


        adminlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(admin.this, dataInsert.class);
                startActivity(i);                        }
                                         });


        btncusedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(admin.this, customerInsert.class);
                startActivity(i);
            }
        });

    }
}
