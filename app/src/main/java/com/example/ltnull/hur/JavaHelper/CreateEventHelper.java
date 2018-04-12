package com.example.ltnull.hur.JavaHelper;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CreateEventHelper extends StringRequest {

    public static final String EVENT_URL_POST = "https://jsunnykhan.000webhostapp.com/tourlouge/postevent.php";
    private Map<String , String> params ;

    public CreateEventHelper (String name , String places , String date, String duration , String cost , String capacity,String number,String description ,String username, Response.Listener<String>listener){
        super(Method.POST , EVENT_URL_POST,listener,null);
        params =new HashMap<>();
        params.put("name",name);
        params.put("places",places);
        params.put("date",date);
        params.put("duration",duration);
        params.put("cost",cost);
        params.put("capacity",capacity);
        params.put("number",number);
        params.put("description",description);
        params.put("username",username);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
