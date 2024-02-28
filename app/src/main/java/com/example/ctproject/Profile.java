package com.example.ctproject;

import android.os.Bundle;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Profile extends Fragment {

    private float point;
    private String name;
    private String user_email;

    public Profile(float points, String username, String email) {
        point = points;
        name = username;
        user_email = email;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        EditText username = view.findViewById(R.id.name);
        EditText email  = view.findViewById(R.id.email);
        EditText points  = view.findViewById(R.id.points);

        username.setText(name);
        email.setText(user_email);
        points.setText(points.toString());

        return view;
    }
}