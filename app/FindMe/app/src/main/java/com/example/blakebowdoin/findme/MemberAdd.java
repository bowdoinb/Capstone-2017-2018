package com.example.blakebowdoin.findme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MemberAdd extends AppCompatActivity {
    String name, creator;
    EditText etMemberAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_add);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(android.graphics.Color.WHITE);

        etMemberAdd = (EditText)findViewById(R.id.etAddMember);

        name = getIntent().getExtras().getString("name");
        creator = getIntent().getExtras().getString("creator");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_new_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();


        if (id == R.id.item1){
            Intent intent = new Intent(this, ViewGroupActivity.class);
            intent.putExtra("username", creator);
            this.startActivity(intent);
            return true;
        }

        if (id == R.id.item2){
            Intent intent = new Intent(this, SettingsActivity.class);
            intent.putExtra("username", creator);
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

    public void onMemberAdd(View view){
        String str_name = name;
        String str_username = etMemberAdd.getText().toString();
        String str_creator = creator;
        String type = "memberAdd";
        BackgroundWorker createRequest = new BackgroundWorker(this);
        createRequest.execute(type, str_name, str_username, str_creator);

        Toast.makeText(this, str_username + " was invited to the group", Toast.LENGTH_LONG).show();
        etMemberAdd.setText("");


    }


}
