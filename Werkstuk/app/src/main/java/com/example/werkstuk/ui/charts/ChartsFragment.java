package com.example.werkstuk.ui.charts;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.werkstuk.DropsByDay;
import com.example.werkstuk.PhoneDrop;
import com.example.werkstuk.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class ChartsFragment extends Fragment {

    private ChartsViewModel chartsViewModel;
    private LineChart phoneDropsByDayLineChart;
    private String chartDataSpecification;
    private TextView chartTitle;
    private NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_charts, container, false);

        assert getArguments() != null;
        chartDataSpecification = getArguments().getString("chart_data_specification");

        chartTitle = root.findViewById(R.id.chart_title);
        chartTitle.setText(chartDataSpecification);
        phoneDropsByDayLineChart = root.findViewById(R.id.phone_drops_by_day_line_chart);


        // live data viewModel configuration
        chartsViewModel = new ViewModelProvider(this).get(ChartsViewModel.class);
        // set custom observer
        if (chartDataSpecification.equals(getString(R.string.drops_this_week_chart_name))) {

            chartsViewModel.getThisWeeksDropsByDate().observe(getViewLifecycleOwner(), new Observer<List<DropsByDay>>() {
                @Override
                public void onChanged(List<DropsByDay> phoneDropsByDay) {
                    drawGraph(phoneDropsByDay);
                }
            });
        } else if (chartDataSpecification.equals(getString(R.string.drops_this_month_chart_name))) {
                chartsViewModel.getThisMonthsDropsByDate().observe(getViewLifecycleOwner(), new Observer<List<DropsByDay>>() {
                    @Override
                    public void onChanged(List<DropsByDay> phoneDropsByDay) {
                        drawGraph(phoneDropsByDay);
                    }
                });
            } else if (chartDataSpecification.equals(getString(R.string.all_drops_chart_name))) {
            chartsViewModel.getAllPhoneDropsByDay().observe(getViewLifecycleOwner(), new Observer<List<DropsByDay>>() {
                @Override
                public void onChanged(List<DropsByDay> phoneDropsByDay) {
                    drawGraph(phoneDropsByDay);
                }
            });
        }

        return root;
    }

    // made with the graphs documentation: https://weeklycoding.com/mpandroidchart-documentation/
    // Draw a graph with the given data and add the missing dates.
    public void drawGraph(List<DropsByDay> phoneDropsByDay){

        String firstRecordedDrop = phoneDropsByDay.get(0).getDateString();
        String lastRecordedDrop = phoneDropsByDay.get(phoneDropsByDay.size() - 1).getDateString();


        final List<String> allDays = chartsViewModel.GetdateStringsBetween(firstRecordedDrop, lastRecordedDrop);

        ArrayList<Entry> dataEntries = new ArrayList<Entry>();
        Integer phoneDropsByDayIndex = 0;



        // loop over each day check if the day is in drops by day else 0 drops that day
        for(int i = 0; i < allDays.size(); i++) {
            if (allDays.get(i).equals(phoneDropsByDay.get(phoneDropsByDayIndex).getDateString())){
                // turn your data into Entry objects
                dataEntries.add(new Entry(i, phoneDropsByDay.get(phoneDropsByDayIndex).getDrops()));
                phoneDropsByDayIndex++;
            }else {
                dataEntries.add(new Entry(i, 0));
            }
        }

        LineDataSet dataSet = new LineDataSet(dataEntries, "PhoneDrops by day");
        dataSet.setColor(Color.RED);

        // set chart description
        phoneDropsByDayLineChart.getDescription().setText("Your phone drops " + firstRecordedDrop.substring(6) + " - " + lastRecordedDrop.substring(6));

        // set x-axis values to dates
        for (int i = 0; i < allDays.size(); i++)
        {
            allDays.set(i, allDays.get(i).substring(0, 5));
        }
        phoneDropsByDayLineChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(allDays));
        phoneDropsByDayLineChart.getXAxis().setAxisMaximum(allDays.size());

        // set chart data
        LineData lineData = new LineData(dataSet);
        phoneDropsByDayLineChart.setData(lineData);
        phoneDropsByDayLineChart.invalidate(); // refresh

    }
}
