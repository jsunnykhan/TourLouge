package com.example.ltnull.hur;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.ltnull.hur.JavaHelper.SessionManager;
import com.example.ltnull.hur.JavaHelper.SignupHelper;

import org.json.JSONException;
import org.json.JSONObject;

public class SigninActivity extends AppCompatActivity {

    private EditText userEt;
    private EditText passEt;
    private EditText nameEt;
    private EditText emailEt;
    SessionManager sessionManager;

    Response.Listener<String> responseListener ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        userEt = findViewById(R.id.userEt);
        passEt = findViewById(R.id.passEt);
        nameEt = findViewById(R.id.nameEt);
        emailEt = findViewById(R.id.emailEt);

        sessionManager =new SessionManager(getApplicationContext());


    }

    public void signupMethod(View view) {

        final String name = nameEt.getText().toString();
        final String username =userEt.getText().toString();
        final String password =passEt.getText().toString();
        final String email =emailEt.getText().toString();


        if(name.isEmpty()){
            Toast.makeText(this, "Fill your Name", Toast.LENGTH_SHORT).show();
        }
        else if(username.isEmpty()){
            Toast.makeText(this, "Enter Username", Toast.LENGTH_SHORT).show();
        }
        else if(password.isEmpty()){
            Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
        }
        else if (email.isEmpty()){
            Toast.makeText(this, "Enter your Email", Toast.LENGTH_SHORT).show();
        }
        else {
            responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject object = new JSONObject(response);
                        boolean success = object.getBoolean("success");
                        if (success) {
                            sessionManager.setLogIn(true);
                            Intent intent = new Intent(SigninActivity.this, HomeActivity.class);
                            SigninActivity.this.startActivity(intent);
                            finish();

                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(SigninActivity.this);
                            builder.setMessage("Register Failed").setNegativeButton("Retry", null).create().show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
        }

        SignupHelper signupHelper = new SignupHelper(username ,password ,name ,email, responseListener);
        RequestQueue queue = Volley.newRequestQueue(SigninActivity.this);
        queue.add(signupHelper);

    }
}
