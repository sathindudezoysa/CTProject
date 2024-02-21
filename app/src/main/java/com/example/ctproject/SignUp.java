package com.example.ctproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.Navigation;

public class SignUp extends Fragment {

    public SignUp() {
        // Required empty public constructor
    }

    EditText name, email, number, password1, password2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        number = view.findViewById(R.id.number);
        password1 = view.findViewById(R.id.password1);
        password2 = view.findViewById(R.id.password2);

        Button signup = view.findViewById(R.id.sumbit);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkFields()){
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());
                    boolean success = dataBaseHelper.addOne(name.toString(),email.toString(),number.toString(),password1.toString());

                    if (success){
                        Toast.makeText(getContext(), "Success", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();

                    }

                    Navigation.findNavController(view).navigate(R.id.action_signUp_to_login);
                }

            }
        });
        return view;
    }

    private boolean checkFields(){
        if ( name.length() == 0){
            name.setError("This field is empty");
            return false;
        }
        if ( email.length() == 0){
            email.setError("This field is empty");
            return false;
        }
        if ( number.length() == 0){
            number.setError("This field is empty");
            return false;
        }
        if ( password1.length() == 0){
            password1.setError("This fied is empty");
            return false;
        }
        if (password1.toString().equals(password2.toString())){
            password2.setError("Password is not matching");
            return false;
        }

        return true;
    }
}