package com.example.werkstuk.ui.charts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.werkstuk.R;

public class ChartsFragment extends Fragment {

    private ChartsViewModel chartsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        chartsViewModel =
                ViewModelProviders.of(this).get(ChartsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_charts, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        chartsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
