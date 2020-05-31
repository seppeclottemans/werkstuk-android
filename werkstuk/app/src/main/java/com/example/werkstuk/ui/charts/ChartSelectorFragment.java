package com.example.werkstuk.ui.charts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.werkstuk.R;

public class ChartSelectorFragment extends Fragment {

    Button allDropsChartNavigatorButton;
    Button DropsThisWeekChartNavigatorButton;
    Button DropsThisMonthChartNavigatorButton;
    NavController navController;
    private View.OnClickListener onButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v != null) {
                Bundle bundle = new Bundle();
                switch (v.getId()) {
                    case R.id.btn_drops_this_week:
                        bundle.putString("chart_data_specification", getString(R.string.drops_this_week_chart_name));
                        break;
                    case R.id.btn_drops_this_month:
                        bundle.putString("chart_data_specification", getString(R.string.drops_this_month_chart_name));
                        break;
                    case R.id.btn_all_drops:
                        bundle.putString("chart_data_specification", getString(R.string.all_drops_chart_name));
                        break;
                }
                navController.navigate(R.id.to_chart_action, bundle);
            }
        }
    };

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

}
