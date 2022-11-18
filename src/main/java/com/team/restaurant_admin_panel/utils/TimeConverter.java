package com.team.restaurant_admin_panel.utils;

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
        SimpleDateFormat monthDate = new SimpleDateFormat("MMM-yyyy");
        Calendar cal = Calendar.getInstance();
        cal.setTime(monthDate.parse(maxDate));
        for (int i = 1; i <= 12; i++) {
            String month_name1 = monthDate.format(cal.getTime());
            allDates.add(month_name1);
            cal.add(Calendar.MONTH, -1);
        }
        Collections.reverse(allDates);
        return allDates;
    }

    public static String getCurrentMonth(){
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MMM-yyyy");
        return df.format(localDate);
    }



}
