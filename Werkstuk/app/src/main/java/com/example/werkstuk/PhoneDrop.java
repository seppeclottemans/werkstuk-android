package com.example.werkstuk;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "phone_drops")
public class PhoneDrop {
    @PrimaryKey(autoGenerate = true)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
