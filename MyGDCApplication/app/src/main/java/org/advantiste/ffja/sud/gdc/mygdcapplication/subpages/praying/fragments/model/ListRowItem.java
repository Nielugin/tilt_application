package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.praying.fragments.model;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.TableRow;
import android.widget.TextView;

import org.advantiste.ffja.sud.gdc.mygdcapplication.model.prayers.Prayer;

/**
 * Created by erc on 23/02/18.
 */

public class ListRowItem extends TableRow {

    private Prayer model;

    private TextView topic;
    private TextView comments;


    public ListRowItem(Context context, Prayer model, int cpt) {
        super(context);
        this.model = model;
        if (cpt%2 == 0)
            this.setBackgroundColor(Color.parseColor("#e6f2ff"));
        else
            this.setBackgroundColor(Color.parseColor("#f2f2f2"));

        this.topic =  new TextView(this.getContext());
        this.addView(topic);
        this.topic.setText(String.valueOf(model.getTopic()));

        if (model.getComments() != null) {
            this.comments = new TextView(this.getContext());
            this.comments.setTypeface(null, Typeface.ITALIC);
            this.addView(comments);
            this.comments.setText(" - " + model.getComments());
        }
        this.setMinimumHeight(45);
    }
}
