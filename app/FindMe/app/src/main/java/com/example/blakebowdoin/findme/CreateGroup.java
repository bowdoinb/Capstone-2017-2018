package com.example.blakebowdoin.findme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class CreateGroup extends AppCompatActivity {
    TextView etGroupName;
    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(android.graphics.Color.WHITE);

        etGroupName = (TextView) findViewById(R.id.etGroupName);
        username = getIntent().getExtras().getString("username");
        //etSearch.setText(username);
    }

    public void OnCreateGroup(View view){
        String str_name = etGroupName.getText().toString();
        String str_username = getIntent().getExtras().getString("username");
        String type = "create";
        BackgroundWorker createRequest = new BackgroundWorker(this);
        createRequest.execute(type, str_name, str_username);
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

        if (id == R.id.item2){
            Intent intent = new Intent(this, SettingsActivity.class);
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
