package com.example.ltnull.hur;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ltnull.hur.PojoClass.CurrentWeatherResponse;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class WeateherActivity extends AppCompatActivity {

    private TextView textView;
    private TextView temp;
    private TextView con;
    private TextView maxmini;

    static double lat, lon;

    private Geocoder geocoder;

     FusedLocationProviderClient client;
     LocationRequest request;
     LocationCallback callback;

     List<Address> addresses ;

    private String CURRENT_API = "http://samples.openweathermap.org/data/2.5/weather?lat=23.7544805&lon=90.3788923&appid=f2b92d0282d3ae4f645dba1e1bc08367";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_google_mapactivity);
        setContentView(R.layout.activity_weateher);

        geocoder = new Geocoder(this);

        textView = findViewById(R.id.weatherTv);
        temp = findViewById(R.id.temperature);
        con = findViewById(R.id.condition);
        maxmini = findViewById(R.id.maxMin);

        client = LocationServices.getFusedLocationProviderClient(this);
        callback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);

                for (Location location : locationResult.getLocations()) {
                    lat = location.getLatitude();
                    lon = location.getLongitude();

                    try {
                      addresses  = geocoder.getFromLocation(lat, lon, 1);
                      String name = addresses.get(0).getAddressLine(0);
                      textView.setText(name+"");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        locationRequest();
        //CURRENT_API = ;
        getWeatherInformation();

    }


    @SuppressLint("RestrictedApi")
    private void locationRequest() {

        request = new LocationRequest()
                .setInterval(5000)
                .setFastestInterval(2500)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},1);
            return;
        }
        client.requestLocationUpdates(request, callback, null);
    }

    private void getWeatherInformation() {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, CURRENT_API, null ,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONObject jsonobj = (JSONObject) response.getJSONArray("weather").get(0);
                            con.setText(jsonobj.getString("description"));

                            double temperature = (response.getJSONObject("main").getDouble("temp"));
                            int tem = (int) (temperature-273.15);
                            temp.setText(tem+"°C");
                            maxmini.setText(response.getJSONObject("main").getString("temp_min") + " / "
                                    + response.getJSONObject("main").getString("temp_max"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(WeateherActivity.this, "Unable to connect", Toast.LENGTH_SHORT).show();
            }
        });

        Volley.newRequestQueue(WeateherActivity.this).add(request);

    }


}
