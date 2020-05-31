package com.example.werkstuk.ui.about_me;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.werkstuk.R;

public class AboutMeFragment extends Fragment {

    private AboutMeViewModel aboutMeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.about_me, container, false);

        return root;
    }
}
