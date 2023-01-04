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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccountFragment extends Fragment {

    private FragmentCreateAccountBinding binding;
    private DBHandler db;
    boolean alreadyExists=false;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    private FirebaseAuth mAuth;


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
        mAuth= FirebaseAuth.getInstance();
        EditText mail=binding.CreateAccountMail;
        EditText pswrd=binding.CreateAccountPassword;
        Button submitButton=binding.CreateAccountButtonCreate;
        Button logInButton= binding.LogInButton;
        submitButton.setOnClickListener( view1 ->{
            String mailText=mail.getText().toString();
            String passwordText=pswrd.getText().toString();

            if (mailText!=""&&passwordText!="") {
                db.addNewUser(mailText, passwordText);
                myRef.child("users").orderByChild("mail").equalTo(mailText).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        alreadyExists=true;
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        userExists(mailText, passwordText);
                    }
                });


            };
            });
        logInButton.setOnClickListener(view1->{
            Navigation.findNavController(getView()).navigate(R.id.action_createAccountFragment_to_logInFragment);
        });

    }
    private void userExists(String mailText, String passwordText){
        if (alreadyExists){
            Toast.makeText(getContext(), "Mail already exists, log in",Toast.LENGTH_SHORT).show();
            alreadyExists=false;
        }
        else {
            mAuth.createUserWithEmailAndPassword(mailText, passwordText).
                    addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            { Toast.makeText(getContext(), "Account Successfully Created, log in",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getContext(), "RegistrationError"+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                            }
                        }
                                                                                               });
                    myRef.child("users").push().setValue(new UserInfo(mailText, passwordText));
            alreadyExists=false;
            Navigation.findNavController(getView()).navigate(R.id.action_createAccountFragment_to_logInFragment);
        }

    }

};
