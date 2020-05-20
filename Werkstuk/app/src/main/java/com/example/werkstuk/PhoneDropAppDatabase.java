package com.example.werkstuk;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = { PhoneDrop.class }, version = 1)
public abstract class PhoneDropAppDatabase extends RoomDatabase {

    public abstract PhoneDropDao dropDao();

    static PhoneDropAppDatabase getInstance(final Context context)
    {
        return Room.databaseBuilder(context.getApplicationContext(),
                PhoneDropAppDatabase.class,
                "dropApplicationdb")
                .allowMainThreadQueries() // not in final version!
                .fallbackToDestructiveMigration() // delete en hermaakt de db elke keer als er een wijziging is
                .build();
    }



}
