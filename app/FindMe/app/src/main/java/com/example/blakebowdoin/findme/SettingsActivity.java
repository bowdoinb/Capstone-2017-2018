package com.example.blakebowdoin.findme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    Switch switchLocation = null;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        username = getIntent().getExtras().getString("username");
        //username = "Zoey1";

        switchLocation = (Switch) findViewById(R.id.switchLocation);
        switchLocation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    //do something
                    Toast.makeText(SettingsActivity.this, "Sharing Location", Toast.LENGTH_LONG).show();

                }else{
                    //do something when unchecked
                    Toast.makeText(SettingsActivity.this, "Stopped Sharing Location", Toast.LENGTH_LONG).show();
                    String type = "toggle";
                    BackgroundWorker toggleRequest = new BackgroundWorker(getBaseContext());
                    toggleRequest.execute(type, username);

                }
            }
        });
    }
}
