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
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.werkstuk.DropsByDay;
import com.example.werkstuk.PhoneDrop;
import com.example.werkstuk.R;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

public class ChartsFragment extends Fragment {

    private ChartsViewModel chartsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_charts, container, false);

        // live data viewModel configuration
        chartsViewModel = new ViewModelProvider(this).get(ChartsViewModel.class);
        chartsViewModel.getAllPhoneDropsByDay().observe(getViewLifecycleOwner(), new Observer<List<DropsByDay>>() {
            @Override
            public void onChanged(List<DropsByDay> phoneDrops) {
                List<DropsByDay> test = phoneDrops;
            }
        });

        return root;
    }

    public void drawGraph(List<PhoneDrop> phoneDrops){

/*        List<Entry> entries = new ArrayList<Entry>();

        for (PhoneDrop phoneDrop : phoneDrops) {

            // turn your data into Entry objects
            entries.add(new Entry(data.getValueX(), data.getValueY()));
        }*/

    }
}
