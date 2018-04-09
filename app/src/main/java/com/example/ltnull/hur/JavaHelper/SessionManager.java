package com.example.ltnull.hur.JavaHelper;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private static final String Tag = SessionManager.class.getSimpleName();
    private static final String Name = "logged";

    Context context;

    private static  final String KEY_IS_LOGGEDIN ="isLoggedInOk";
    private static  final String KEY_IS_USERNAME ="userName";


    public SessionManager(Context context){
        this.context = context ;
        sharedPreferences = context.getSharedPreferences(Name , Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public void setLogIn(boolean isLoggedIn){
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.commit();
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(KEY_IS_LOGGEDIN , false);
    }

    public void setUserName(String userName){
        editor.putString(KEY_IS_USERNAME,userName);
        editor.commit();
    }

    public  String getKeyIsUsername() {
        return sharedPreferences.getString(KEY_IS_USERNAME , null);
    }
}
