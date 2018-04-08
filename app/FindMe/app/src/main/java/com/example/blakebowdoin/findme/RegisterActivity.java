package com.example.blakebowdoin.findme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    EditText Name, Username, Email, Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Name = (EditText)findViewById(R.id.etName);
        Username = (EditText)findViewById(R.id.etUsername);
        Email = (EditText)findViewById(R.id.etEmail);
        Password = (EditText)findViewById(R.id.etPassword);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("FindMe");
    }



    public void onRegister(View view){
        String str_name = Name.getText().toString();
        String str_username = Username.getText().toString();
        String str_password = Password.getText().toString();
        String str_email = Email.getText().toString();
        String type = "register";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_name, str_username, str_password, str_email);
        /*if (backgroundWorker
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);*/
    }

}

