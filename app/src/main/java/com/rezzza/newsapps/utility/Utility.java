package com.rezzza.newsapps.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utility {

    public static Date getDate(String pDate, String inputFormat){
        if (pDate.length() > 19){
            pDate = pDate.substring(0,19);
        }
        DateFormat format = new SimpleDateFormat(inputFormat, Locale.getDefault());
        try {
            return format.parse(pDate.replace("T"," ").split("\\.")[0]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static String getDateString(Date date, String sFormat){
        DateFormat format = new SimpleDateFormat(sFormat, Locale.getDefault());
        return format.format(date);
    }

}
