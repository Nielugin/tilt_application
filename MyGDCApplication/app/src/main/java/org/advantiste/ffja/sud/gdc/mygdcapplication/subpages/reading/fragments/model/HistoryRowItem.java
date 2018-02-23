package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.fragments.model;

import android.content.Context;
import android.graphics.Color;
import android.widget.TableRow;
import android.widget.TextView;

import org.advantiste.ffja.sud.gdc.mygdcapplication.controller.TimeServices;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.WeeklyReading;


/**
 * Created by jean- on 29/12/2017.
 */

public class HistoryRowItem extends TableRow {

    private WeeklyReading model;

    private TextView weekNumber;
    private TextView readingDate;

    public HistoryRowItem(Context context, WeeklyReading model) {
        super(context);
        this.model = model;
        if (model.getWeekNumber()%2 == 0)
            this.setBackgroundColor(Color.parseColor("#e6f2ff"));
        else
            this.setBackgroundColor(Color.parseColor("#f2f2f2"));

        this.weekNumber =  new TextView(this.getContext());
        this.addView(weekNumber);
        this.weekNumber.setText("Semaine "+String.valueOf(model.getWeekNumber())+" : ");

        this.readingDate =  new TextView(this.getContext());
        this.addView(readingDate);
        this.readingDate.setText("Du "+TimeServices.getDateString(model.getBeginDate())
                +" au "+TimeServices.getDateString(model.getEndDate()));

        this.setMinimumHeight(45);
    }


}
