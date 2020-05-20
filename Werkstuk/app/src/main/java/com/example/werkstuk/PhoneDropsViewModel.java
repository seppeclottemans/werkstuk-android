package com.example.werkstuk;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

// View model documentation used: https://developer.android.com/topic/libraries/architecture/viewmodel
public class PhoneDropsViewModel extends ViewModel {
    private MutableLiveData<Integer> total;
    public LiveData<Integer> getTotalAmount() {
        if (total == null) {
            total = new MutableLiveData<Integer>();
            loadNumberOfDrops();
        }
        return total;
    }

    private void loadNumberOfDrops() {

    }


}
