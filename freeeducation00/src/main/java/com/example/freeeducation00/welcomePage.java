package com.example.freeeducation00;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.freeeducation00.databinding.FragmentCreateAccountBinding;
import com.example.freeeducation00.databinding.FragmentWelcomePageBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link welcomePage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class welcomePage extends Fragment implements MyRecyclerViewAdapter.ItemClickListener {
    private FragmentWelcomePageBinding binding;
    MyRecyclerViewAdapter adapter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
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
    public static welcomePage newInstance(String param1, String param2) {
        welcomePage fragment = new welcomePage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentWelcomePageBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView myRecyclerView= binding.recyclerView;
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<Technology> technologies = new ArrayList<>();
        technologies.add(new Technology("Python", "The easiest language to start with!",R.drawable.python));
        technologies.add(new Technology("C++", "The easiest language to start with!",R.drawable.cpp));
        technologies.add(new Technology("Java", "The easiest language to start with!",R.drawable.java));

        technologies.add(new Technology("Unity", "The easiest language to start with!",R.drawable.unity));

        technologies.add(new Technology("Html", "The easiest language to start with!",R.drawable.html));

        myRecyclerView.setAdapter(new MyRecyclerViewAdapter(getContext(), technologies));
        adapter = new MyRecyclerViewAdapter(getContext(), technologies);
        adapter.setClickListener((MyRecyclerViewAdapter.ItemClickListener) this);
        myRecyclerView.setAdapter(adapter);

    }
    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getContext(), "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }

}