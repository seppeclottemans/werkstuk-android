package com.example.werkstuk.ui.charts;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.werkstuk.MainActivity;
import com.example.werkstuk.R;
import com.example.werkstuk.ui.home.HomeViewModel;

public class ChartSelectorFragment extends Fragment {

    Button allDropsChartNavigatorButton;
    Button DropsThisWeekChartNavigatorButton;
    Button DropsThisMonthChartNavigatorButton;
    NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_chart_selector, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        allDropsChartNavigatorButton = view.findViewById(R.id.btn_all_drops);
        allDropsChartNavigatorButton.setOnClickListener(onButtonClickListener);
        DropsThisWeekChartNavigatorButton = view.findViewById(R.id.btn_drops_this_week);
        DropsThisWeekChartNavigatorButton.setOnClickListener(onButtonClickListener);
        DropsThisMonthChartNavigatorButton = view.findViewById(R.id.btn_drops_this_month);
        DropsThisMonthChartNavigatorButton.setOnClickListener(onButtonClickListener);
    }

    private View.OnClickListener onButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v != null){
                Bundle bundle = new Bundle();
                switch (v.getId()) {
                    case R.id.btn_drops_this_week:
                        bundle.putString("chart_data_specification", "Drops this week");
                        break;
                    case R.id.btn_drops_this_month:
                        bundle.putString("chart_data_specification", "Drops this month");
                        break;
                    case R.id.btn_all_drops:
                        bundle.putString("chart_data_specification", "All Phone Drops");
                        break;
                }
                navController.navigate(R.id.to_chart_action, bundle);
            }
        }
    };

}
