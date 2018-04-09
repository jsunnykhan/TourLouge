package com.example.ltnull.hur;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ltnull.hur.Adapter.HomeAdapter;
import com.example.ltnull.hur.PojoClass.HomePojo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    private static  final  String NEWS_API_URL = "https://jsunnykhan.000webhostapp.com/tourlouge/newsfeed.php";

    private Context context;
    private RecyclerView recyclerView ;
    private HomeAdapter adapter ;

    List<HomePojo> list;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView =view.findViewById(R.id.listViewForHome);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        list = new ArrayList<>();
        loadNews();
        return view;
    }

    private void loadNews() {

        StringRequest request = new StringRequest(Request.Method.GET, NEWS_API_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray object = new JSONArray(response);

                    for(int i=0 ; i<object.length() ; i++){
                        JSONObject jsonObject = object.getJSONObject(i);
                        list.add(new HomePojo(jsonObject.getInt("id"),
                                jsonObject.getString("image"),
                                jsonObject.getString("name"),
                                jsonObject.getString("time"),
                                jsonObject.getString("news")));
                    }
                    HomeAdapter homeAdapter = new HomeAdapter(getContext() , list);
                    recyclerView.setAdapter(homeAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Unable to connect ", Toast.LENGTH_SHORT).show();

            }
        });
        Volley.newRequestQueue(context).add(request);
    }

}
