package com.example.werkstuk;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DropsByDay {
    private String dateString;
    private Integer drops;

    public DropsByDay(Integer drops, String dateString) {
        this.drops = drops;
        this.dateString = dateString;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public Integer getDrops() {
        return drops;
    }

    public void setDrops(Integer drops) {
        this.drops = drops;
    }
}
