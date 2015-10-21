/**
 * Created by beckp on 9/10/2558.
 */
package com.cgc.Util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import java.text.DateFormat;

public class CheckTime {


    public String ChkDate(String Status) throws Exception
    {


        Calendar now = Calendar.getInstance();

        String Return_Val = "0";

        Date now2 = new Date();

        int hour = now.get(Calendar.HOUR_OF_DAY); // Get hour in 24 hour format
        int minute = now.get(Calendar.MINUTE);

        Date date = parseDate(hour + ":" + minute);
        Date dateCompareOne = parseDate("08:00");
        Date dateCompareTwo = parseDate("20:00");

        System.out.println("------------------------------");

        System.out.println("hour = " + hour);

        System.out.println("date = " + date);
        System.out.println("dateCompareOne = " + dateCompareOne);
        System.out.println("dateCompareTwo = " + dateCompareTwo);
        System.out.println("------------------------------");

        System.out.println("now2 = " + DateFormat.getTimeInstance(DateFormat.SHORT).format(now2));

        if (dateCompareOne.before(date) && dateCompareTwo.after(date)) {
            Return_Val = "1";
        }

        return Return_Val;
    }

    public Date parseDate(String date) {

        final String inputFormat = "HH:mm";
        SimpleDateFormat inputParser = new SimpleDateFormat(inputFormat);
        try {
            return inputParser.parse(date);
        } catch (java.text.ParseException e) {
            return new Date(0);
        }
    }
}
