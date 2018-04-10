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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class SettingsActivity extends AppCompatActivity {

    Switch switchLocation = null;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        switchLocation = (Switch) findViewById(R.id.switchLocation);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(android.graphics.Color.WHITE);

        username = getIntent().getExtras().getString("username");

        try {
            String str_username = username;
            String toggleState_url = "http://cgi.soic.indiana.edu/~team48/FindMeToggleState.php";
            URL url = new URL(toggleState_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            String result = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            if(result.equalsIgnoreCase("True")){
                OnLocation();
            } else{
                OffLocation();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    public void OnLocation(){
        switchLocation.setChecked(true);
    }

    public void OffLocation(){
        switchLocation.setChecked(false);
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
