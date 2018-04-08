package com.example.blakebowdoin.findme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(android.graphics.Color.WHITE);

        username = getIntent().getExtras().getString("username");
        //username = "Zoey1";

        switchLocation = (Switch) findViewById(R.id.switchLocation);
        switchLocation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    //do something
                    Toast.makeText(SettingsActivity.this, "Sharing Location", Toast.LENGTH_LONG).show();
                    String type = "toggleOn";
                    BackgroundWorker toggleRequest = new BackgroundWorker(getBaseContext());
                    toggleRequest.execute(type, username);

                }else{
                    //do something when unchecked
                    Toast.makeText(SettingsActivity.this, "Stopped Sharing Location", Toast.LENGTH_LONG).show();
                    String type = "toggleOff";
                    BackgroundWorker toggleRequest = new BackgroundWorker(getBaseContext());
                    toggleRequest.execute(type, username);

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_view_groups, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
//        private void enableService() {
//        Intent intent = new Intent(getApplicationContext(), GPS_Service.class);
//        intent.putExtra("username", username);
//        startService(intent);
//    }

        if (id == R.id.item1){
            Intent intent = new Intent(this, CreateGroup.class);
            intent.putExtra("username", username);
            this.startActivity(intent);
            return true;
        }

        if (id == R.id.item3){
            Intent intent = new Intent(this, LoginActivity.class);
            this.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
