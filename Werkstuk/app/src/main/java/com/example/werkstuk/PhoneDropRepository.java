package com.example.werkstuk;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PhoneDropRepository {

    private PhoneDropDao phoneDropDao;
    private LiveData<Integer> total;
    private LiveData<List<PhoneDrop>> allPhoneDrops;
    private LiveData<List<DropsByDay>> allPhoneDropsByDay;

    public PhoneDropRepository(Application application){
        PhoneDropAppDatabase database = PhoneDropAppDatabase.getInstance(application);
        phoneDropDao = database.phoneDropDao();
        total = phoneDropDao.getDropCount();
        allPhoneDrops = phoneDropDao.getAllDrops();
        allPhoneDropsByDay = phoneDropDao.GetDropsByDate();
    }

    // live data objects
    public LiveData<Integer> getTotal(){
        return  total;
    }

    public LiveData<List<PhoneDrop>> getAllPhoneDrops(){
        return  allPhoneDrops;
    }

    public LiveData<List<DropsByDay>> getAllPhoneDropsByDay(){
        return  allPhoneDropsByDay;
    }

    // query's
    public void insert(PhoneDrop phoneDrop){
        new  InsertPhoneDropAsyncTask(phoneDropDao).execute(phoneDrop);
    }

    // async tasks
    private static class InsertPhoneDropAsyncTask extends AsyncTask<PhoneDrop, Void, Void>{
        private PhoneDropDao phoneDropDao;

        private InsertPhoneDropAsyncTask(PhoneDropDao phoneDropDao){
            this.phoneDropDao = phoneDropDao;
        }

        @Override
        protected Void doInBackground(PhoneDrop... phoneDrops) {
            phoneDropDao.insert(phoneDrops[0]);
            return null;
        }
    }

}
