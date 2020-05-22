package com.example.werkstuk.ui.charts;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.werkstuk.DropsByDay;
import com.example.werkstuk.PhoneDrop;
import com.example.werkstuk.PhoneDropRepository;

import java.util.List;

public class ChartsViewModel extends AndroidViewModel {

    private PhoneDropRepository repository;
    private LiveData<List<PhoneDrop>> allPhoneDrops;
    private LiveData<List<DropsByDay>> allDropsByDay;

    public ChartsViewModel(@NonNull Application application) {
        super(application);
        repository = new PhoneDropRepository(application);
        allPhoneDrops = repository.getAllPhoneDrops();
        allDropsByDay = repository.getAllPhoneDropsByDay();
    }

    public LiveData<List<PhoneDrop>> getAllPhoneDrops() {
        return allPhoneDrops;
    }

    public LiveData<List<DropsByDay>> getAllPhoneDropsByDay() {
        return allDropsByDay;
    }

}