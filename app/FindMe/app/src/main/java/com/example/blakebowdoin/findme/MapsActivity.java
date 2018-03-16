package com.example.blakebowdoin.findme;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String GroupID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        GroupID = getIntent().getExtras().getString("GroupID");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        getJSON("http://cgi.soic.indiana.edu/~team48/FindMeGetLocations.php");
        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


    private void getJSON(final String urlWebService){

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPostExecute(String s){
                //super.onPostExecute(s);
                //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                try{
                    loadIntoMap(s);
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
                    String post_data = URLEncoder.encode("GroupID", "UTF-8")+"="+URLEncoder.encode(GroupID, "UTF-8");
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

    public void loadIntoMap(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);

        for (int i = 0; i < jsonArray.length(); i++) {
            // Create a marker for each city in the JSON data.
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            mMap.addMarker(new MarkerOptions()
                    .title(jsonObj.getString("Username"))
                    .position(new LatLng(
                            jsonObj.getJSONArray("lat").getDouble(1),
                            jsonObj.getJSONArray("lng").getDouble(2)
                    )));


        }
    }

}
