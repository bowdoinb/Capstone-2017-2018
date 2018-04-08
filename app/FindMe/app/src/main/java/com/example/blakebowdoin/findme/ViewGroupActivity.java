package com.example.blakebowdoin.findme;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class ViewGroupActivity extends AppCompatActivity {

    ListView listView;
    String username;


    //String[] listValue = new String[] {"ONE","TWO","THREE","FOUR","FIVE","SIX","SEVEN","EIGHT"};


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(android.graphics.Color.WHITE);

        listView = (ListView) findViewById(R.id.listView);

        //enableService();

        getJSON("http://cgi.soic.indiana.edu/~team48/FindMeViewGroups.php");

        username = getIntent().getExtras().getString("username");






//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
//                String item = ((TextView)view).getText().toString();
//
//                //Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();
//
//                String TempListViewClickedValue = groupid[position].toString();
//                Intent intent = new Intent(ViewGroupActivity.this, MapActivity.class);
//                intent.putExtra("ListViewClickedValue", TempListViewClickedValue);
//                startActivity(intent);
//
//                //startActivity(new Intent(ViewGroupActivity.this, MapActivity.class));
//                //Intent MapActivityIntent = new Intent(ViewGroupActivity.this, MapActivity.class);
//                //MapActivityIntent.putExtra("my.package.dataToPass", dataFromClickedRow);
//                //startActivity(MapActivityIntent);
//            }
//        });
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

    private void getJSON(final String urlWebService){

        class GetJSON extends AsyncTask<Void, Void, String>{

            @Override
            protected void onPostExecute(String s){
                //super.onPostExecute(s);
                //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
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
                    con.setRequestMethod("POST");
                    con.setDoOutput(true);
                    con.setDoInput(true);
                    OutputStream outputStream = con.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("username", "UTF-8")+"="+URLEncoder.encode(username, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
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

    public void loadIntoListView(String json) throws JSONException{
        JSONArray jsonArray = new JSONArray(json);

        final String[] groups = new String[jsonArray.length()];
        final String[] groupid = new String[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject obj = jsonArray.getJSONObject(i);
            groups[i] = obj.getString("Name");
        }

        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject obj = jsonArray.getJSONObject(i);
            groupid[i] = obj.getString("GroupID");
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                String item = ((TextView)view).getText().toString();
                //Bundle extras = new Bundle();

                //Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();

                String TempListViewClickedValue = groupid[position].toString();
                String TempListViewName = groups[position].toString();
                Intent intent = new Intent(ViewGroupActivity.this, MapsActivity.class);
                intent.putExtra("GroupID", TempListViewClickedValue);
                intent.putExtra("username", username);
                intent.putExtra("name", TempListViewName);
                //intent.putExtras(extras);
                startActivity(intent);

                //startActivity(new Intent(ViewGroupActivity.this, MapActivity.class));
                //Intent MapActivityIntent = new Intent(ViewGroupActivity.this, MapActivity.class);
                //MapActivityIntent.putExtra("my.package.dataToPass", dataFromClickedRow);
                //startActivity(MapActivityIntent);
            }
        });

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, groups);
        listView.setAdapter(arrayAdapter);
    }

    @Override

    protected void onResume() {
        super.onResume();
        getJSON("http://cgi.soic.indiana.edu/~team48/FindMeViewGroups.php");
    }

}
