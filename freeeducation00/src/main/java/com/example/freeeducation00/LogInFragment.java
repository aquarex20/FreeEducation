package com.example.freeeducation00;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.freeeducation00.databinding.FragmentLogInBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 */
public class LogInFragment extends Fragment {
    private FragmentLogInBinding binding;
    private DBHandler dbHandler;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();


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

        String logInMail= binding.logInMail.getText().toString();
        String logInPassword= binding.logInPassword.getText().toString();

        binding.logInButton.setOnClickListener(view1 -> {
            if (!logInMail.isEmpty() &&!logInPassword.isEmpty())
            {
                if (myRef.child("users").orderByChild("mail").equalTo(logInMail).get().isSuccessful())

                    Toast.makeText(getActivity(), "Mail exists",Toast.LENGTH_SHORT).show();

                else{
                    Toast.makeText(getActivity(), dbHandler.searchMessage,Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(), "That works baby girl :)",Toast.LENGTH_SHORT).show();

                }

            }
        });
        binding.createAccountButton.setOnClickListener(view1 -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_logInFragment_to_createAccountFragment);


        });
    }
}