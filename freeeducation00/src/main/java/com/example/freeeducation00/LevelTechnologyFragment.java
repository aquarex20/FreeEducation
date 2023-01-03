package com.example.freeeducation00;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.freeeducation00.databinding.FragmentLevelTechnologyBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LevelTechnologyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LevelTechnologyFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parametersD
    private String mParam1;
    private String mParam2;

    private FragmentLevelTechnologyBinding binding;
    MyTaskRecyclerViewAdapter adapter;
    private ArrayList<Task> tasks;
    public LevelTechnologyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LevelTechnologyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LevelTechnologyFragment newInstance(String param1, String param2) {
        LevelTechnologyFragment fragment = new LevelTechnologyFragment();
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
        binding= FragmentLevelTechnologyBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView myRecyclerView=binding.taskRecyclerView;
        tasks=new ArrayList<>();
        tasks.add(new Task(mParam1, mParam1, mParam1, mParam1));
        tasks.add(new Task(mParam1, mParam1, mParam1, mParam1));
        tasks.add(new Task(mParam1, mParam1, mParam1, mParam1));
        tasks.add(new Task(mParam1, mParam1, mParam1, mParam1));
        tasks.add(new Task(mParam1, mParam1, mParam1, mParam1));
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myRecyclerView.setAdapter(new MyTaskRecyclerViewAdapter(getContext(), tasks));
        adapter = new MyTaskRecyclerViewAdapter(getContext(), tasks);
        //adapter.setClickListener((MyTaskRecyclerViewAdapter.ItemClickListener) binding.getRoot());
        myRecyclerView.setAdapter(adapter);

    }
}