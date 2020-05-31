package com.example.werkstuk;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class MainViewModel extends AndroidViewModel {
    private PhoneDropRepository repository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new PhoneDropRepository(application);
    }

    public void insert(PhoneDrop phoneDrop) {
        repository.insert(phoneDrop);
    }

}
