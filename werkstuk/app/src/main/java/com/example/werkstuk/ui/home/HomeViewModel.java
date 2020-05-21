package com.example.werkstuk.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.werkstuk.PhoneDrop;
import com.example.werkstuk.PhoneDropRepository;

public class HomeViewModel extends AndroidViewModel {

    private PhoneDropRepository repository;
    private LiveData<Integer> total;

    public HomeViewModel(@NonNull Application application) {
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