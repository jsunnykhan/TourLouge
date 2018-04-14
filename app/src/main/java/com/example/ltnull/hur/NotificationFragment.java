package com.example.ltnull.hur;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ltnull.hur.Adapter.EventAdapter;
import com.example.ltnull.hur.PojoClass.EventPojo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {

    private static final String EVENT_URL = "https://jsunnykhan.000webhostapp.com/tourlouge/eventDataFetch.php";

    private  Context context ;
    private RecyclerView recyclerView;
    private EventAdapter adapter ;
    private List<EventPojo> events ;


    public NotificationFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_notification, container, false);

        recyclerView = view.findViewById(R.id.eventListForEvent);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        events = new ArrayList<>();
        loadEventData();
        return view;
    }

    private void loadEventData() {

        StringRequest request = new StringRequest(Request.Method.GET, EVENT_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);

                        events.add(new EventPojo(object.getString("name"),
                                object.getString("place"),
                                object.getString("duration"),
                                object.getString("number"),
                                object.getString("auth")));
                    }
                    EventAdapter eventAdapter = new EventAdapter(getContext(), events);
                    recyclerView.setAdapter(eventAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Failed to Connect", Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(context).add(request);
    }

}
