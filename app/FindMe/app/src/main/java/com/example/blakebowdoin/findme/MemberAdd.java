package com.example.blakebowdoin.findme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        etMemberAdd = (EditText)findViewById(R.id.etAddMember);

        name = getIntent().getExtras().getString("name");
        creator = getIntent().getExtras().getString("creator");
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
