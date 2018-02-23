package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.fragments.model;

import android.content.Context;
import android.graphics.Color;
import android.widget.TableRow;
import android.widget.TextView;

import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.BibleBook;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.WeeklyReading;

import java.util.List;

/**
 * Created by erc on 29/12/2017.
 */

public class HistoryRowItemBook extends TableRow {

    private WeeklyReading model;

    private TextView bookChaptersR;

    private TextView blank;


    public HistoryRowItemBook(Context context, WeeklyReading model, BibleBook book) {
        super(context);
        this.model = model;
        if (model.getWeekNumber()%2 == 0)
            this.setBackgroundColor(Color.parseColor("#e6f2ff"));
        else
            this.setBackgroundColor(Color.parseColor("#f2f2f2"));

        this.blank = new TextView(this.getContext());
        this.addView(blank);
        this.blank.setText("");

        this.bookChaptersR = new TextView(this.getContext());
        this.addView(bookChaptersR);
        List<Integer> chapters = model.getReadingDetails().get(book);
        this.bookChaptersR.setText(book.name()+"."+String.valueOf(chapters.get(0)) + "-"
                + String.valueOf(chapters.get(chapters.size() - 1)));

        this.setMinimumHeight(45);
    }


}
