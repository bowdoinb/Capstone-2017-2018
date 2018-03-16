package com.example.blakebowdoin.findme;

import android.*;
import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Joel Shellabarger on 1/21/2018.
 */

public class LoginActivity extends AppCompatActivity {
    EditText etUsername, etPassword;
    Button bLogin;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        final TextView registerLink = (TextView) findViewById(R.id.tvRegisterHere);

    }



    public void OnLogin(View view){
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String type = "login";
        BackgroundWorker loginRequest = new BackgroundWorker(this);
        loginRequest.execute(type, username, password);

    }
    public void OpenReg(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }



}
