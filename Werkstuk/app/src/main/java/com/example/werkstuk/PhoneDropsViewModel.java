package com.example.werkstuk;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

// View model documentation used: https://developer.android.com/topic/libraries/architecture/viewmodel
public class PhoneDropsViewModel extends AndroidViewModel {
    private PhoneDropRepository repository;
    private LiveData<Integer> total;

    public PhoneDropsViewModel(@NonNull Application application) {
        super(application);
        repository = new PhoneDropRepository(application);
        total = repository.getTotal();
    }

    public void insert(PhoneDrop phoneDrop){
        repository.insert(phoneDrop);
    }

    public LiveData<Integer> getTotal() {
        return total;
    }

}
