package com.example.freeeducation00;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.freeeducation00.databinding.FragmentCreateAccountBinding;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccountFragment extends Fragment {

    private FragmentCreateAccountBinding binding;
    private DBHandler db;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();


    public CreateAccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentCreateAccountBinding.inflate(inflater, container, false);
        db= new DBHandler(getContext());
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_log_in, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText mail=binding.CreateAccountMail;
        EditText pswrd=binding.CreateAccountPassword;
        Button submitButton=binding.CreateAccountButtonCreate;
        Button logInButton= binding.LogInButton;
        submitButton.setOnClickListener(view1 ->{
            String mailText=mail.getText().toString();
            String passwordText=pswrd.getText().toString();

            if (mailText!=""&&passwordText!="") {
                db.addNewUser(mailText, passwordText);
                Log.d("tester", (String) myRef.child("users").orderByChild("mail").equalTo(mailText).limitToFirst(1).get().getResult().getValue());

                if (myRef.child("users").orderByChild("mail").equalTo(mailText).get().isSuccessful()){
                    Toast.makeText(getContext(), "Mail already exists, log in",Toast.LENGTH_SHORT).show();

                    return;
                }
                else {
                    myRef.child("users").push().setValue(new UserInfo(mailText, passwordText));
                    Toast.makeText(getContext(), "Account Successfully Created, log in",Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(getView()).navigate(R.id.action_createAccountFragment_to_logInFragment);

                }

            }
        });
        logInButton.setOnClickListener(view1->{
            Navigation.findNavController(getView()).navigate(R.id.action_createAccountFragment_to_logInFragment);
        });

    }
};
