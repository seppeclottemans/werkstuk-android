package com.example.werkstuk;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DropDao {
    @Insert
    void insert(Drop... drops);
}
