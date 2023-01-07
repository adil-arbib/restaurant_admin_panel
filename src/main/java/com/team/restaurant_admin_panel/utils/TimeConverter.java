package com.team.restaurant_admin_panel.utils;

import com.team.restaurant_admin_panel.models.statistics.Statistics;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public final class TimeConverter {
    private TimeConverter(){}

    public static ArrayList<String> getLast12Months(String maxDate) throws ParseException {
        ArrayList<String> allDates = new ArrayList<>();
        SimpleDateFormat monthDate = new SimpleDateFormat("yyyy-M");
        Calendar cal = Calendar.getInstance();
        cal.setTime(monthDate.parse(maxDate));
        for (int i = 1; i <= 12; i++) {
            String month_name1 = monthDate.format(cal.getTime());
            allDates.add(month_name1);
            cal.add(Calendar.MONTH, -1);
        }
        return allDates;
    }
    public static String getLastMonth() {
        LocalDate now = LocalDate.now(); // 2022-11-19
        LocalDate earlier = now.minusMonths(1); // 2022-10-19
        DateTimeFormatter df ;
        //DateTimeFormatter.ofPattern("yyyy-MM");
        if(now.getMonthValue() < 10){
            df = DateTimeFormatter.ofPattern("yyyy-M");
        } else {
            df = DateTimeFormatter.ofPattern("yyyy-MM");
        }

        earlier.getMonth(); // java.time.Month = OCTOBER
        earlier.getMonth().getValue(); // 10
        earlier.getYear(); // 2015
        return df.format(earlier);
    }


    public static String getCurrentMonth(){
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter df;
        if(localDate.getMonthValue() < 10){
            df = DateTimeFormatter.ofPattern("yyyy-M");
            return df.format(localDate);
        }
        df = DateTimeFormatter.ofPattern("yyyy-MM");
        return df.format(localDate);
    }
    public static String getCurrentYear(){
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy");
        return df.format(localDate);
    }
    public static String getLastYear(){
        LocalDate now = LocalDate.now(); // 2022-11-19
        LocalDate earlier = now.minusYears(1); // 2021-11-19
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy");
        earlier.getMonth(); // java.time.Month = OCTOBER
        earlier.getMonth().getValue(); // 10
        earlier.getYear(); // 2015
        return df.format(earlier);
    }

/*
    public static void main(String[] args) throws ParseException {
        System.out.println(getLast12Months("2023-1"));
        System.out.println(getLast12Months("2023-01"));

    }
    */

}
