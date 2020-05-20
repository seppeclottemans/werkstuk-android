package com.example.werkstuk;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "drops")
public class Drop {
    @PrimaryKey(autoGenerate = true)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
