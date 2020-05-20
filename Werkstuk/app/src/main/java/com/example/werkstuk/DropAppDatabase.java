package com.example.werkstuk;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = { Drop.class }, version = 1)
public abstract class DropAppDatabase extends RoomDatabase {

    static DropAppDatabase getInstance(final Context context)
    {
        return Room.databaseBuilder(context.getApplicationContext(),
                DropAppDatabase.class,
                "dropApplicationdb")
                .allowMainThreadQueries() // not in final version!
                .fallbackToDestructiveMigration() // delete en hermaakt de db elke keer als er een wijziging is
                .build();
    }



}
