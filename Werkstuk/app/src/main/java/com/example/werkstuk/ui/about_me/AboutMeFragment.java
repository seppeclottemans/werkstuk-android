package com.example.werkstuk.ui.about_me;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.werkstuk.R;

public class AboutMeFragment extends Fragment {

    private CheckBox customCheckBox;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.about_me, container, false);

        customCheckBox = root.findViewById(R.id.custom_checkbox);
        customCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(customCheckBox.isChecked()){
                    Toast.makeText(getContext(), "Thank You!", Toast.LENGTH_LONG).show();
                }
            }
        });

        return root;
    }


}
