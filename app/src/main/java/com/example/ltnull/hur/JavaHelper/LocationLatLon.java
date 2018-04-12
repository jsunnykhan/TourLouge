package com.example.ltnull.hur.JavaHelper;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Observable;

public class LocationLatLon {

    private final Context context;
    private FusedLocationProviderClient client;
    private double lat , lon ;

    public double getLat() {
        return lat;
    }



    public double getLon() {
        return lon;
    }



    public LocationLatLon(Context context) {
        client = LocationServices.getFusedLocationProviderClient(context);
        this.context = context;
    }

    public void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION
                    ,Manifest.permission.ACCESS_FINE_LOCATION},1);
            return;
        }
        client.getLastLocation().addOnSuccessListener((Activity) context, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    lat = location.getLatitude();
                    lon = location.getLongitude();
                    Toast.makeText(context, lat+"    "+lon, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
