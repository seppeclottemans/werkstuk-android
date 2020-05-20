package com.example.werkstuk;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface PhoneDropDao {
    @Insert
    void insert(PhoneDrop... phoneDrops);

    @Query("SELECT COUNT(id) FROM phone_drops")
    LiveData<Integer> getDropCount();
}
