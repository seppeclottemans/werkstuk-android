package com.example.werkstuk.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.werkstuk.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private TextView dropCounter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        dropCounter = root.findViewById(R.id.drop_counter);

        // live data viewModel configuration
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.getTotal().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                dropCounter.setText(String.valueOf(integer));
            }
        });

        return root;
    }
}
