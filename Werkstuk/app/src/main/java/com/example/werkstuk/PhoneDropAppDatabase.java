package com.example.werkstuk;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Calendar;

// for setup tutorial used (Room + ViewModel + LiveData + RecyclerView (MVVM)): https://www.youtube.com/channel/UC_Fh8kvtkVPkeihBs42jGcA
@Database(entities = { PhoneDrop.class }, version = 1, exportSchema = false)
@TypeConverters({TimestampConverter.class})
public abstract class PhoneDropAppDatabase extends RoomDatabase {

    private static PhoneDropAppDatabase instance;

    public abstract PhoneDropDao phoneDropDao();

    static PhoneDropAppDatabase getInstance(final Context context)
    {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PhoneDropAppDatabase.class,
                    "db_phone_drop")
                    .fallbackToDestructiveMigration()
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
            Calendar calender = Calendar.getInstance();
            phoneDropDao.insert(new PhoneDrop(calender.getTime()));
            phoneDropDao.insert(new PhoneDrop(calender.getTime()));
            calender.add(Calendar.DATE, -2);
            phoneDropDao.insert(new PhoneDrop(calender.getTime()));
            calender.add(Calendar.DATE, -3);
            phoneDropDao.insert(new PhoneDrop(calender.getTime()));
            phoneDropDao.insert(new PhoneDrop(calender.getTime()));
            return null;
        };
    }

}
