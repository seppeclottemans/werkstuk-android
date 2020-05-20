package com.example.werkstuk;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

public class PhoneDropRepository {

    private PhoneDropDao phoneDropDao;
    private LiveData<Integer> total;

    public PhoneDropRepository(Application application){
        PhoneDropAppDatabase database = PhoneDropAppDatabase.getInstance(application);
        phoneDropDao = database.phoneDropDao();
        total = phoneDropDao.getDropCount();
    }

    public void insert(PhoneDrop phoneDrop){
        new  InsertPhoneDropAsyncTask(phoneDropDao).execute(phoneDrop);
    }

    public LiveData<Integer> getTotal(){
        return  total;
    }

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
