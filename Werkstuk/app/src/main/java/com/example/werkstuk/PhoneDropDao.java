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

    @Query("SELECT count(*) as drops, strftime('%Y-%m-%d', created_at) as dateString FROM phone_drops_table GROUP BY dateString")
    LiveData<List<DropsByDay>> GetDropsByDate();

    // get this weeks data: https://stackoverflow.com/questions/9487835/get-this-weeks-data-using-sqlite
    @Query("SELECT count(*) as drops, strftime('%Y-%m-%d', created_at) as dateString FROM phone_drops_table WHERE dateString >= DATE('now', 'weekday 0', '-7 days') GROUP BY dateString ")
    LiveData<List<DropsByDay>> GetThisWeeksDropsByDate();

    // get this months data: https://stackoverflow.com/questions/36820890/select-data-from-current-month-in-android-with-sqlite
    @Query("SELECT count(*) as drops, strftime('%Y-%m-%d', created_at) as dateString FROM phone_drops_table where strftime('%Y',created_at)= strftime('%Y',date('now')) and strftime('%m',created_at ) = strftime('%m',date('now')) GROUP BY dateString ")
    LiveData<List<DropsByDay>> GetThisMonthsDropsByDate();
}
