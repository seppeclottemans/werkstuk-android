package com.example.werkstuk;

import androidx.room.TypeConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

// Code from this class: https://android.jlelse.eu/5-steps-to-implement-room-persistence-library-in-android-47b10cd47b24
// Convert date to string and string to date tos save in the database.
public class TimestampConverter {
    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @TypeConverter
    public static Date fromTimestamp(String value) {
        if (value != null) {
            try {
                TimeZone timeZone = TimeZone.getDefault();
                df.setTimeZone(timeZone);
                return df.parse(value);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            return null;
        }
    }


    @TypeConverter
    public static String dateToTimestamp(Date value) {
        TimeZone timeZone = TimeZone.getDefault();
        df.setTimeZone(timeZone);
        return value == null ? null : df.format(value);
    }

}
