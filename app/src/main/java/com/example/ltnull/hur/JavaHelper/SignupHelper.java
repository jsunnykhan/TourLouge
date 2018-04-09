package com.example.ltnull.hur.JavaHelper;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SignupHelper extends StringRequest {

    public static final String SIGN_UP_URL = "https://jsunnykhan.000webhostapp.com/tourlouge/login.php";
    private Map<String , String> params;

    public SignupHelper (String username , String password , String name , String email , Response.Listener<String>listener){
        super(Method.POST,SIGN_UP_URL,listener,null);
        params =new HashMap<>();
        params.put("username",username);
        params.put("password",password);
        params.put("name",name);
        params.put("email",email);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
