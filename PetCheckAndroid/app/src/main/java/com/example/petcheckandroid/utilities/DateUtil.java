package com.example.petcheckandroid.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static String setDateTime(Date date){

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm aaa", Locale.US);
        String currentDT = sdf.format(date);

        return currentDT;
    }

}
