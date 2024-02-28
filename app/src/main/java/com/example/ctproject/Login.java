package com.example.ctproject;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.Navigation;

public class Login extends Fragment {
    public Login() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        EditText username = view.findViewById(R.id.username);
        EditText password = view.findViewById(R.id.password);

        Button signup = view.findViewById(R.id.signup);
        Button userlogin = view.findViewById(R.id.userlogin);

        userlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass = password.getText().toString();
                String name = username.getText().toString();
//                String dpass = dataBaseHelper.getpass(name);
                if(pass.equals("1234")){
                    Toast.makeText(getContext(), "login successfull", Toast.LENGTH_LONG).show();
                    Navigation.findNavController(view).navigate(R.id.action_login_to_home2);
                }else {
                    Toast.makeText(getContext(), "Username or password does not match", Toast.LENGTH_LONG).show();
                }
//                Navigation.findNavController(view).navigate(R.id.action_login_to_home2);
            }

        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_login_to_signUp);
            }
        });

        return view;
    }
}