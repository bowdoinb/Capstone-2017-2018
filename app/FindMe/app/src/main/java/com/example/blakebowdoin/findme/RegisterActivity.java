package com.example.blakebowdoin.findme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
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

    //Activate when a user clicks the Register Button
    public void onRegister(View view){
        String str_name = Name.getText().toString();
        String str_username = Username.getText().toString();
        String str_password = Password.getText().toString();
        String str_email = Email.getText().toString();
        OnRegisterClick(str_name, str_username, str_password, str_email);
//        String type = "register";
//        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
//        backgroundWorker.execute(type, str_name, str_username, str_password, str_email);
    }

    //The function that is called by the User's click, then depending on if the Username is taken or not calls the corresponding
    // Function.
    public String OnRegisterClick(String name, String username, String password, String email) {
        try {
            String str_name = name;
            String str_username = username;
            String str_password = password;
            String str_email = email;
            String register_url = "http://cgi.soic.indiana.edu/~team48/FindMeRegister.php";
            URL url = new URL(register_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(str_name, "UTF-8") + "&" +
                    URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(str_username, "UTF-8") + "&"
                    + URLEncoder.encode("user_pass", "UTF-8") + "=" + URLEncoder.encode(str_password, "UTF-8")
                    + "&" + URLEncoder.encode("user_email", "UTF-8") + "=" + URLEncoder.encode(str_email, "UTF-8");
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
            if(result.equalsIgnoreCase("Successful")){
                OnRegisterSuccess();
            } else{
                OnRegisterFailed();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //If the User's chosen Username is not already taken, calls this function, takes them to the Login Screen
    public void OnRegisterSuccess(){
        Toast.makeText(RegisterActivity.this, "Register successful", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    //If the User's chosen Username is already taken, calls this function
    public void OnRegisterFailed(){
        Toast.makeText(RegisterActivity.this, "Username already taken!", Toast.LENGTH_LONG).show();
    }

}

