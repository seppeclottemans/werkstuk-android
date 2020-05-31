package com.example.werkstuk.ui.charts;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.werkstuk.DropsByDay;
import com.example.werkstuk.PhoneDropRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ChartsViewModel extends AndroidViewModel {

    private PhoneDropRepository repository;
    private LiveData<List<DropsByDay>> allDropsByDay;
    private LiveData<List<DropsByDay>> thisWeeksDropsByDate;
    private LiveData<List<DropsByDay>> thisMonthsDropsByDate;

    public ChartsViewModel(@NonNull Application application) {
        super(application);
        repository = new PhoneDropRepository(application);
        allDropsByDay = repository.getAllPhoneDropsByDay();
        thisWeeksDropsByDate = repository.getThisWeeksDropsByDate();
        thisMonthsDropsByDate = repository.getThisMonthsDropsByDate();
    }

    // live data objects
    public LiveData<List<DropsByDay>> getAllPhoneDropsByDay() {
        return allDropsByDay;
    }

    public LiveData<List<DropsByDay>> getThisWeeksDropsByDate() {
        return thisWeeksDropsByDate;
    }

    public LiveData<List<DropsByDay>> getThisMonthsDropsByDate() {
        return thisMonthsDropsByDate;
    }


    // function code inspired by: https://stackoverflow.com/questions/11412713/generate-dates-between-two-date-in-android (29/05/2020)
    // this function returns a list of date strings of dates between 2 date strings.
    public List<String> GetdateStringsBetween(String startDateString, String endDateString) {
        ArrayList<String> dateStrings = new ArrayList<String>();
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

        Calendar startDateCalender = Calendar.getInstance();
        Calendar endDateCalender = Calendar.getInstance();

        try {
            startDateCalender.setTime(formater.parse(startDateString));
            endDateCalender.setTime(formater.parse(endDateString));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        while (!startDateCalender.after(endDateCalender)) {
            dateStrings.add(formater.format(startDateCalender.getTime()));
            startDateCalender.add(Calendar.DATE, 1);
        }
        return dateStrings;
    }

}