package com.example.freeeducation00;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freeeducation00.databinding.FragmentDisplayTechnologyBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DisplayTechnologyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisplayTechnologyFragment extends Fragment implements MyRecyclerViewAdapter.ItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "technology";

    // TODO: Rename and change types of parameters
    private Technology mParam1;

    private FragmentDisplayTechnologyBinding binding;
    MyTaskRecyclerViewAdapter adapter;

    public DisplayTechnologyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment DisplayTechnologyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DisplayTechnologyFragment newInstance(Technology param1) {
        DisplayTechnologyFragment fragment = new DisplayTechnologyFragment();
        Bundle args = new Bundle();
        Log.d("asd", param1.getTitle());
        Log.d("asd", "bkqskaksdkasd");

        args.putParcelable(ARG_PARAM1, param1);
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
        mParam1 = getArguments().getParcelable(ARG_PARAM1);

        binding=FragmentDisplayTechnologyBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView technologyImage=binding.technologyPresentationPageImage;
        TextView technologyTitle= binding.technologyPresentationPageTitle;
        TextView technologyDescription=binding.technologyPresentationPageDescription;
        technologyImage.setImageResource(Math.toIntExact(mParam1.getImage()));
        technologyDescription.setText(mParam1.getDescription());
        technologyTitle.setText(mParam1.getTitle());
        Button beginnerButton= binding.beginnerLevelButton;
        Button intermediateButton= binding.intermediateLevelButton;
        Button advancedButton= binding.advancedLevelButton;
        FragmentContainerView containerView= binding.LevelContainerView;
        beginnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("param1","beginner");
                bundle.putString("param2","beginner");

                Navigation.findNavController(containerView).navigate(R.id.action_levelTechnologyFragment_self,bundle );
            }

        });
        intermediateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("param1","intermediate");
                Navigation.findNavController(containerView).navigate(R.id.action_levelTechnologyFragment_self,bundle );
            }

        });
        advancedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("param1","advanced");
                Navigation.findNavController(containerView).navigate(R.id.action_levelTechnologyFragment_self,bundle );
            }
        });
    }
    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getContext(), "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }

}