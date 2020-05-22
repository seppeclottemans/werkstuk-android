package com.example.werkstuk;

import java.util.Date;

public class DropsByDay {
    private Date date;
    private Integer drops;

    public DropsByDay(Date date, Integer drops) {
        this.date = date;
        this.drops = drops;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getDrops() {
        return drops;
    }

    public void setDrops(Integer drops) {
        this.drops = drops;
    }
}
