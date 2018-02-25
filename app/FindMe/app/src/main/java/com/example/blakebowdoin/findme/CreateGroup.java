package com.example.blakebowdoin.findme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

public class CreateGroup extends AppCompatActivity {
    TextView etGroupName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        etGroupName = (TextView) findViewById(R.id.etGroupName);
        //etSearch.setText(username);
    }

    public void OnCreateGroup(View view){
        String str_name = etGroupName.getText().toString();
        String str_username = getIntent().getExtras().getString("username");
        String type = "create";
        BackgroundWorker createRequest = new BackgroundWorker(this);
        createRequest.execute(type, str_name, str_username);
    }
}
