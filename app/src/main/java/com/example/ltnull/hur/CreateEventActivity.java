package com.example.ltnull.hur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.ltnull.hur.JavaHelper.CreateEventHelper;
import com.example.ltnull.hur.JavaHelper.SessionManager;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class CreateEventActivity extends AppCompatActivity {

    private EditText name;
    private EditText places;
    private EditText statDate;
    private EditText duration;
    private EditText cost ;
    private EditText capacity;
    private EditText number;
    private EditText description;

    private String Name;
    private String Places;
    private String StartDate;
    private String Duration;
    private String Cost ;
    private String Capacity;
    private String Number;
    private String Description ;
    private String username;

    private Response.Listener<String> listener ;
    private SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        name = findViewById(R.id.EventName);
        places = findViewById(R.id.EventPlace);
        name = findViewById(R.id.EventName);
        statDate = findViewById(R.id.EventJoiningDate);
        duration = findViewById(R.id.EventDuration);
        cost = findViewById(R.id.EventCost);
        capacity = findViewById(R.id.EventCapacity);
        number = findViewById(R.id.EventNumber);
        description = findViewById(R.id.EventDescription);

        sessionManager = new SessionManager(CreateEventActivity.this);
    }

    public void CreateAnEvent(View view) {
        Name = name.getText().toString();
        Places = places.getText().toString();
        StartDate = statDate.getText().toString();
        Duration = duration.getText().toString();
        Cost = cost.getText().toString();
        Capacity = capacity.getText().toString();
        Number = number.getText().toString();
        Description = description.getText().toString();
        username = sessionManager.getKeyIsUsername();


        if(Name.isEmpty()&&Places.isEmpty()&&StartDate.isEmpty()&&Duration.isEmpty()&&
                Cost.isEmpty()&&Capacity.isEmpty()&&Number.isEmpty()&&Description.isEmpty()){
            Toast.makeText(this, "One or more field missing", Toast.LENGTH_SHORT).show();

        }
        else{

            listener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject object = new JSONObject(response);
                        boolean success  = object.getBoolean("success");
                        if (success){
                            Toast.makeText(CreateEventActivity.this, "Success", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(CreateEventActivity.this, "Failed to Create event /n check Internet Connection", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            };

        }

        CreateEventHelper helper = new CreateEventHelper(Name ,Places , StartDate , Duration , Cost , Capacity ,Number ,Description ,username, listener);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(helper);



    }
}
