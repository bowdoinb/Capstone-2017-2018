package com.example.blakebowdoin.findme;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;

/**
 * Created by blakebowdoin on 3/15/18.
 */

//public class GPS_Service extends Service {
//
//    private LocationListener listener;
//    private LocationManager locationManager;
//
//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent){
//        return null;
//    }
//
//    @SuppressLint("MissingPermission")
//    @Override
//    public void onCreate() {
//        listener = new LocationListener() {
//            @Override
//            public void onLocationChanged(Location location) {
//                Intent i = new Intent("LocationUpdate");
//                i.putExtra("coordinates", location.getLatitude()+" "+ location.getLongitude());
//                sendBroadcast(i);
//
//            }
//
//            @Override
//            public void onStatusChanged(String s, int i, Bundle bundle) {
//
//            }
//
//            @Override
//            public void onProviderEnabled(String s) {
//
//            }
//
//            @Override
//            public void onProviderDisabled(String s) {
//                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(i);
//
//            }
//        };
//
//        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
//
//        //no inspection MissingPermission
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15000, 10, listener);
//    }
//}
