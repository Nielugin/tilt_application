package org.advantiste.ffja.sud.gdc.mygdcapplication.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jean- on 29/12/2017.
 */

public class TimeServices {

    public static String getDateString(long timestamp){
        Date date = new Date(timestamp);
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yy");


        return formater.format(date);
    }

}
