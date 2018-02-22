package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.fragments.model;

import android.content.Context;
import android.sax.TextElementListener;
import android.widget.TableRow;
import android.widget.TextView;

import org.advantiste.ffja.sud.gdc.mygdcapplication.R;
import org.advantiste.ffja.sud.gdc.mygdcapplication.controller.TimeServices;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.BibleBook;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.WeeklyReading;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by jean- on 29/12/2017.
 */

public class HistoryRowItem extends TableRow {

    private WeeklyReading model;

    private TextView weekNumber;

    private TextView chapterCount;

    private TextView readingDate;


    public HistoryRowItem(Context context, WeeklyReading model) {
        super(context);
        this.model = model;
        this.weekNumber =  new TextView(this.getContext());
        this.addView(weekNumber);
        this.weekNumber.setText("Semaine "+String.valueOf(model.getWeekNumber())+" : ");

        this.chapterCount = new TextView(this.getContext());
        this.addView(chapterCount);
        BibleBook book;
        Map<BibleBook, List<Integer>> weekRead = model.getReadingDetails();
        Iterator<BibleBook> itBooks = weekRead.keySet().iterator();
        if (itBooks.hasNext()) {
            book = itBooks.next();
            List<Integer> chapters = weekRead.get(book);
            String chaptersText = book.name() + " " + String.valueOf(chapters.get(0)) + "-" + String.valueOf(chapters.get(chapters.size() - 1));
            while (itBooks.hasNext()) {
                book = itBooks.next();
                chapters = weekRead.get(book);
                chaptersText += ", " + book.name() + " " + String.valueOf(chapters.get(0)) + "-" + String.valueOf(chapters.get(chapters.size() - 1));
            }
            this.chapterCount.setText(chaptersText + " ");
        } else {
            this.chapterCount.setText(" Aucune lecture ");
        }

        this.readingDate = new TextView(this.getContext());
        this.addView(readingDate);
        this.readingDate.setText(TimeServices.getDateString(model.getBeginDate()));

        this.setMinimumHeight(75);
    }


}
