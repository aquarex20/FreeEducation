package com.example.freeeducation00;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import com.example.freeeducation00.databinding.FragmentCreateAccountBinding;
import com.example.freeeducation00.databinding.FragmentWelcomePageBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link welcomePage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class welcomePage extends Fragment implements MyRecyclerViewAdapter.ItemClickListener {
    private FragmentWelcomePageBinding binding;
    MyRecyclerViewAdapter adapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    private ArrayList<Technology> technologies = new ArrayList<>();


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "technologies";

    // TODO: Rename and change types of parameters
    private ArrayList<Technology> mParam1;
    private String mParam2;

    public welcomePage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment welcomePage.
     */
    // TODO: Rename and change types and number of parameters
    public static welcomePage newInstance(ArrayList<Technology> param1, String param2) {
        welcomePage fragment = new welcomePage();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        technologies= (ArrayList<Technology>) getArguments().getSerializable(ARG_PARAM1);
        // Inflate the layout for this fragment
        binding= FragmentWelcomePageBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView myRecyclerView= binding.recyclerView;
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myRecyclerView.setAdapter(new MyRecyclerViewAdapter(getContext(), technologies));
        adapter = new MyRecyclerViewAdapter(getContext(), technologies);
        adapter.setClickListener((MyRecyclerViewAdapter.ItemClickListener) this);
        myRecyclerView.setAdapter(adapter);

    }
    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getContext(), "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
        Technology clickedTechnology=new Technology(adapter.getItem(position).getTitle(),adapter.getItem(position).getDescription(), adapter.getItem(position).getImage());
        Bundle bundle = new Bundle();
        bundle.putParcelable("technology",  clickedTechnology);
        Navigation.findNavController(getView()).navigate(R.id.action_welcomePage_to_displayTechnologyFragment,bundle);
    }

}