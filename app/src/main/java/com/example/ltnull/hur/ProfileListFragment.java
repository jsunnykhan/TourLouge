package com.example.ltnull.hur;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ltnull.hur.JavaHelper.SessionManager;
import com.example.ltnull.hur.JavaHelper.TimeHelper;


public class ProfileListFragment extends Fragment {

    private ImageView imageView;
    private TextView profile , create ,friend, weather , maps , nearby ,logout ;
    private Context context;

    private SessionManager sessionManager;
    private TimeHelper  helper ;


    public ProfileListFragment() {
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_list, container, false);

        imageView = view.findViewById(R.id.imageTV);
        profile = view.findViewById(R.id.profileTv);
        create  = view.findViewById(R.id.createEvent);
        friend  = view.findViewById(R.id.friendTv);
        weather  = view.findViewById(R.id.weather);
        maps  = view.findViewById(R.id.maps);
        nearby  =view.findViewById(R.id.nearby);
        logout  = view.findViewById(R.id.logout);
        sessionManager = new SessionManager(getContext());
        helper = new TimeHelper();



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.setLogIn(false);
                Intent intent = new Intent(context , LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });




        //profile
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , ProfileActivity.class);
                startActivity(intent);
            }
        });




        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });





        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });





        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });





        nearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });






        return  view;
    }

}
