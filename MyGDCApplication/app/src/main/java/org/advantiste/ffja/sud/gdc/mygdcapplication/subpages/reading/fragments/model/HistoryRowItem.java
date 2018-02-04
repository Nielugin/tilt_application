package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.fragments.model;

import android.content.Context;
import android.sax.TextElementListener;
import android.widget.TableRow;
import android.widget.TextView;

import org.advantiste.ffja.sud.gdc.mygdcapplication.R;
import org.advantiste.ffja.sud.gdc.mygdcapplication.controller.TimeServices;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.WeeklyReading;

import java.util.Collection;
import java.util.List;

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
        this.weekNumber.setText("Semaine "+String.valueOf(model.getWeekNumber())+" ");

        this.chapterCount = new TextView(this.getContext());
        this.addView(chapterCount);

        int count=0;
        Collection<List<Integer>> values = model.getReadingDetails().values();
        for (List<Integer> value:
            values ) {
            count+=value.size();
        }

        this.chapterCount.setText(String.valueOf(count)+" ");

        this.readingDate = new TextView(this.getContext());
        this.addView(readingDate);
        this.readingDate.setText(TimeServices.getDateString(model.getBeginDate()));

        this.setMinimumHeight(75);
    }


}
