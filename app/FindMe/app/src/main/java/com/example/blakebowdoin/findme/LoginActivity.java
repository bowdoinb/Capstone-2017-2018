package com.example.blakebowdoin.findme;

import android.*;
import android.Manifest;
import android.annotation.SuppressLint;
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
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
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




    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        final TextView registerLink = (TextView) findViewById(R.id.tvRegisterHere);

        requestPermissions();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean requestPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET
            }, 1 );
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return true;
        } else {
            return false;
        }

    }

//    @SuppressLint({"MissingPermission", "NewApi"})
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
//        switch(requestCode){
//            case 1:
//                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    enableService();
//                } else {
//                    requestPermissions();
//                }
//
//
//        }
//    }

//    private void enableService() {
//        Intent intent = new Intent(getApplicationContext(), GPS_Service.class);
//        startService(intent);
//    }


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
