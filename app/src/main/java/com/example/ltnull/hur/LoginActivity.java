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
import com.example.ltnull.hur.JavaHelper.LoginHelper;
import com.example.ltnull.hur.JavaHelper.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    EditText user;
    EditText pass;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = findViewById(R.id.userEt);
        pass = findViewById(R.id.passEt);
        sessionManager = new SessionManager(LoginActivity.this);

        if(sessionManager.isLoggedIn()){
            Intent intent = new Intent(LoginActivity.this , HomeActivity.class);
            LoginActivity.this.startActivity(intent);
            finish();
        }


    }

    public void SigninMethod(View view) {
        final String username = user.getText().toString();
        String password = pass.getText().toString();

        Response.Listener<String> respnoseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    boolean success = object.getBoolean("success");
                    if(success){
                        String name = object.getString("name");
                        String email = object.getString("email");
                        sessionManager.setLogIn(true);
                        sessionManager.setUserName(username);

                        Intent intent = new Intent(LoginActivity.this , HomeActivity.class);
                        LoginActivity.this.startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "Failed to connect try again", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        LoginHelper loginHelper = new LoginHelper(username , password ,respnoseListener );
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        queue.add(loginHelper);
    }

    public void forgetPasswordMethod(View view) {
    }

    public void createAccountMethod(View view) {
        Intent intent = new Intent(LoginActivity.this , SigninActivity.class);
        startActivity(intent);

    }
}
