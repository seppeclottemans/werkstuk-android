package com.example.werkstuk;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PhoneDropDao {
    @Insert
    void insert(PhoneDrop... phoneDrops);

    @Update
    void update(PhoneDrop... phoneDrops);

    @Delete
    void delete(PhoneDrop phone_drops);

    @Query("SELECT COUNT(id) FROM phone_drops_table")
    LiveData<Integer> getDropCount();

    @Query("SELECT * FROM phone_drops_table")
    LiveData<List<PhoneDrop>> getAllDrops();

    @Query("SELECT count(*) as drops, strftime('%d-%m-%Y', created_at) as date FROM phone_drops_table GROUP BY date")
    LiveData<List<DropsByDay>> GetDropsByDate();
}
