package com.example.ctproject;

import android.os.Bundle;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.Navigation;

public class home extends Fragment {

    private int state = 0;

    public home() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button connect = view.findViewById(R.id.connect);
        Button start = view.findViewById(R.id.start);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_home2_to_BDevices);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(state == 0)
                {
                    start.setText("Stop");

                    state = 1;
                }else
                {
                    start.setText("Start");
                    state = 0;
                }
            }
        });

        return view;
    }
}