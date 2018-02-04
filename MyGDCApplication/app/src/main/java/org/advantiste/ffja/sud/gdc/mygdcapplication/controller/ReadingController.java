package org.advantiste.ffja.sud.gdc.mygdcapplication.controller;

import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.BibleBook;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.BibleBookChapterAssociation;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.WeeklyReading;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jean- on 29/12/2017.
 */

public class ReadingController {

    private static ReadingController readingController;

    private List<WeeklyReading> readings;

    private long oneWeek = 604800000;

    public static ReadingController getInstance() {
        if(readingController==null){
            readingController= new ReadingController();
        }
        return readingController;
    }

    private ReadingController(){
        this.readings = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        long beginDate = calendar.getTimeInMillis();
        calendar.setTimeInMillis(beginDate+oneWeek);
        long endDate =  calendar.getTimeInMillis();


        BibleBook[] values = BibleBook.values();
        for (int i = 0; i < 10; i++) {
            BibleBookChapterAssociation bibleBookChapterAssociation = new BibleBookChapterAssociation();
            Integer actCount = bibleBookChapterAssociation.getBibleIntegerEnumMap().get(values[i]);
            WeeklyReading weeklyReading = new WeeklyReading(beginDate, endDate,"", i, new HashMap<BibleBook, List<Integer>>());
            readings.add(weeklyReading);
        }


    }

    public List<WeeklyReading> getReadings() {
        return readings;
    }
}
