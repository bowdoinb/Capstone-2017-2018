package com.example.blakebowdoin.findme;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ViewGroupActivity extends AppCompatActivity {

    ListView listView;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group);

        listView = (ListView) findViewById(R.id.listView);
        getJSON("http://cgi.soic.indiana.edu/~jshellab/ViewGroups.php");
    }

    private void getJSON(final String urlWebService){

        class GetJSON extends AsyncTask<Void, Void, String>{

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
                try{
                    loadIntoListView(s);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void...voids){

                try{
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection)url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json=bufferedReader.readLine()) != null){
                        sb.append(json + "\n");
                    }
                    return  sb.toString().trim();

                } catch (Exception e){
                    return null;
                }
            }
        }

        GetJSON getJSON = new GetJSON();
        getJSON.execute();

    }

    private void loadIntoListView(String json) throws JSONException{
        JSONArray jsonArray = new JSONArray(json);

        String[] groups = new String[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject obj = jsonArray.getJSONObject(i);
            groups[i] = obj.getString("Name");
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, groups);
        listView.setAdapter(arrayAdapter);
    }
}
