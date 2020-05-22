package com.example.werkstuk.ui.charts;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class ChartsFragment extends Fragment {

    private ChartsViewModel chartsViewModel;
    private LineChart phoneDropsByDayLineChart;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_charts, container, false);

        phoneDropsByDayLineChart = root.findViewById(R.id.phone_drops_by_day_line_chart);

        // live data viewModel configuration
        chartsViewModel = new ViewModelProvider(this).get(ChartsViewModel.class);
        chartsViewModel.getAllPhoneDropsByDay().observe(getViewLifecycleOwner(), new Observer<List<DropsByDay>>() {
            @Override
            public void onChanged(List<DropsByDay> phoneDropsByDay) {
                drawGraph(phoneDropsByDay);
            }
        });

        return root;
    }

    public void drawGraph(List<DropsByDay> phoneDropsByDay){

        List<Entry> dataEntries = new ArrayList<Entry>();

        for(int i = 0; i < phoneDropsByDay.size(); i++) {
            // turn your data into Entry objects
            dataEntries.add(new Entry(i, phoneDropsByDay.get(i).getDrops()));
        }

        LineDataSet dataSet = new LineDataSet(dataEntries, "PhoneDrops by day");
        dataSet.setColor(Color.RED);

        LineData lineData = new LineData(dataSet);
        phoneDropsByDayLineChart.setData(lineData);
        phoneDropsByDayLineChart.invalidate(); // refresh

    }
}
