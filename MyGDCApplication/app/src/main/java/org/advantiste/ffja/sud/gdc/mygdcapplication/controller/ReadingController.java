package org.advantiste.ffja.sud.gdc.mygdcapplication.controller;

import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.BibleBook;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.BibleBookChapterAssociation;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.WeeklyReading;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        BibleBookChapterAssociation bbca = new BibleBookChapterAssociation();
        HashMap<BibleBook, Integer> bookChapterNumber = bbca.getBibleIntegerEnumMap();
        BibleBook[] values = BibleBook.values();
        for (int i = 0; i < 10; i++) {
            BibleBookChapterAssociation bibleBookChapterAssociation = new BibleBookChapterAssociation();
            Integer actCount = bibleBookChapterAssociation.getBibleIntegerEnumMap().get(values[i]);
            Map<BibleBook, List<Integer>> weekRead = new HashMap<>();
            BibleBook book = values[(int) (Math.round(Math.random()*values.length))];
            List<Integer> listChapters = new ArrayList<>();
            for (int j = 1; j <= bookChapterNumber.get(book); ++j)
                listChapters.add(j);
            weekRead.put(book, listChapters);
            if (listChapters.size() < 15){
                book = values[(int) (Math.round(Math.random()*values.length))];
                listChapters = new ArrayList<>();
                for (int j = 1; j <= bookChapterNumber.get(book); ++j)
                    listChapters.add(j);
                weekRead.put(book, listChapters);
            }
            WeeklyReading weeklyReading = new WeeklyReading(beginDate, endDate,"", i, weekRead);
            readings.add(weeklyReading);
        }


    }

    public List<WeeklyReading> getReadings() {
        return readings;
    }
}
