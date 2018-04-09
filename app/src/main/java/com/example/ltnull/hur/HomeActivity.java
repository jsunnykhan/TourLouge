package com.example.ltnull.hur;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.ltnull.hur.JavaHelper.SessionManager;

public class HomeActivity extends AppCompatActivity {

    private SessionManager sessionManager;
    private BottomNavigationView bottomNavigationView ;
    private HomeFragment homeFragment;
    private NotificationFragment notificationFragment;
    private ProfileListFragment profileListFragment;
    private FrameLayout layout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sessionManager= new SessionManager(getApplicationContext());
        bottomNavigationView = findViewById(R.id.homeNavigationBar);
        layout = findViewById(R.id.navFragmentContainer);
        homeFragment = new HomeFragment();
        notificationFragment = new NotificationFragment();
        profileListFragment = new ProfileListFragment();

        setFragment(homeFragment);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.newsFeed:
                        setFragment(homeFragment);
                        return true;

                    case R.id.notificationNav:
                        setFragment(notificationFragment);
                        return true;
                    case R.id.viewNav:
                        setFragment(profileListFragment);
                        return true;

                    default:
                            return false;
                }
            }
        });

    }

    public void setFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.navFragmentContainer , fragment);
        transaction.commit();
    }

}
