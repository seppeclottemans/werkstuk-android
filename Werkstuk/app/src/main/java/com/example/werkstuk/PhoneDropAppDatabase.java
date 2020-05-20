package com.example.werkstuk;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = { PhoneDrop.class }, version = 1, exportSchema = false)
public abstract class PhoneDropAppDatabase extends RoomDatabase {

    private static PhoneDropAppDatabase instance;

    public abstract PhoneDropDao phoneDropDao();

    static PhoneDropAppDatabase getInstance(final Context context)
    {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PhoneDropAppDatabase.class,
                    "db_phone_drop")
                    .fallbackToDestructiveMigration() // delete en hermaakt de db elke keer als er een wijziging is
                    .addCallback(phoneDropCallBack)
                    .build();
        }
        return instance;
    }


    // test for populating the database
    private static PhoneDropAppDatabase.Callback phoneDropCallBack = new PhoneDropAppDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulatePhoneDropDbAsyncTask(instance).execute();
        }
    };

    private static class PopulatePhoneDropDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private PhoneDropDao phoneDropDao;

        private PopulatePhoneDropDbAsyncTask(PhoneDropAppDatabase db){
            phoneDropDao = db.phoneDropDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            phoneDropDao.insert(new PhoneDrop());
            phoneDropDao.insert(new PhoneDrop());
            phoneDropDao.insert(new PhoneDrop());
            return null;
        };
    }

}
