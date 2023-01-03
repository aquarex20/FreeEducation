package com.example.freeeducation00;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.freeeducation00.databinding.FragmentLogInBinding;
import com.example.freeeducation00.databinding.FragmentWelcomePageBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 */
public class LogInFragment extends Fragment {
    private FragmentLogInBinding binding;
    private DBHandler dbHandler;
    private ArrayList<Technology> technologies = new ArrayList<>();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    private void validateUser(HashMap valeurTrouvee){
        EditText logInMail= binding.logInMail;
        EditText logInPassword= binding.logInPassword;
        Button logInButton=binding.logInButton;
        String logInMailText= logInMail.getText().toString();
        String logInPasswordText= logInPassword.getText().toString();
        Map<String, String> doubleBraceMap  = new HashMap<String, String>() {{
            put("mail", logInMailText);
            put("password", logInPasswordText);
        }};
        if (valeurTrouvee.equals(doubleBraceMap)){
            myRef.child("technologies").orderByKey().addChildEventListener(dataRecollectionListener);
            myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("technologies",  technologies);
                    Navigation.findNavController(getView()).navigate(R.id.action_logInFragment_to_welcomePage,bundle);
                }
            });


        }
        else{
            logInButton.setEnabled(true);
        }


    }

    public LogInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentLogInBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_log_in, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dbHandler = new DBHandler(getActivity());

        EditText logInMail= binding.logInMail;
        EditText logInPassword= binding.logInPassword;
         Button logInButton= binding.logInButton;
        logInButton.setOnClickListener(view1 -> {
            if (!logInMail.getText().toString().isEmpty() &&!logInPassword.getText().toString().isEmpty())
            {
                logInButton.setEnabled(false);
                String logInMailText= logInMail.getText().toString();
                myRef.child("users").orderByChild("mail").equalTo(logInMailText).addChildEventListener(myChildListener);

            }
        });
        binding.createAccountButton.setOnClickListener(view1 -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_logInFragment_to_createAccountFragment);
        });

    }
    ChildEventListener dataRecollectionListener= new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            HashMap monSnap= (HashMap)snapshot.getValue();
            technologies.add(new Technology( monSnap));


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
    };
    ChildEventListener myChildListener=new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            validateUser((HashMap) snapshot.getValue());
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
    };

}