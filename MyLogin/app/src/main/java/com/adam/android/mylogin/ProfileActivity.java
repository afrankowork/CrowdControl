package com.adam.android.mylogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static com.google.android.gms.auth.api.credentials.CredentialPickerConfig.Prompt.SIGN_IN;


public class ProfileActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {


    private TextView name;
    private TextView email;
    private TextView id;
    private Button signOutBtn;
    String email1;
    String name1;
    private Button btnSignIn;
    private Button btnlocation;
    String server_url_insert = "https://cgi.sice.indiana.edu/~afrankow/insert.php";
    boolean message = false;



    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        name = findViewById(R.id.name);
        email = findViewById(R.id.email);

        signOutBtn = findViewById(R.id.signOutBtn);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnlocation = findViewById(R.id.btnlocation);


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        if (status.isSuccess())
                            gotoMainActivity();
                        else
                            Toast.makeText(ProfileActivity.this, "Log out Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                try {
                    Add();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }




            }
        });


        btnlocation.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                    gotoLocationList();

            }
        });

    }

    private void gotoMainActivity() {

        startActivity(new Intent(ProfileActivity.this, MainActivity.class));
        finish();
    }

    private void gotoLocationList() {
        String email2 = email.getText().toString();
        Intent i=new Intent(ProfileActivity.this, locations.class);
        i.putExtra("email",email2);

        startActivity(i);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            name.setText(account.getDisplayName());
            email.setText(account.getEmail());




        } else {
            gotoMainActivity();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()) {
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult result) {
                    handleSignInResult(result);
                }
            });

        }


    }

    private void Add() throws UnsupportedEncodingException {
        String name1 = URLEncoder.encode(name.getText().toString(), "UTF8");
        String email1 = URLEncoder.encode(email.getText().toString(), "UTF8");

        String url = server_url_insert + "?pro_name=" + name1 + "&pro_email=" + email1 + "";
        Log.e("URL", url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(ProfileActivity.this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ProfileActivity.this, "e" + e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProfileActivity.this, "err" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(ProfileActivity.this);
        requestQueue.add(stringRequest);
    }






}






