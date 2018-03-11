package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.praying.fragments.model;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.advantiste.ffja.sud.gdc.mygdcapplication.R;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.prayers.Prayer;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.prayers.PrayerDataSource;
import org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.praying.fragments.PrayingFragmentList;

import static android.R.drawable.ic_delete;

/**
 * Created by erc on 23/02/18.
 */

public class ListRowItem extends TableRow {

    private final ListRowListener lrl;
    private Prayer model;

    private TextView topic;
    private TextView comments;
    private ImageButton removeTopic;

    private PrayerDataSource dataSource;



    public interface ListRowListener {
        void updateList();
    }


    public ListRowItem(final Context context, final Prayer model, int cpt, final ListRowListener lrl) {
        super(context);
        this.lrl = lrl;
        this.model = model;
        if (cpt%2 == 0)
            this.setBackgroundColor(Color.parseColor("#e6f2ff"));
        else
            this.setBackgroundColor(Color.parseColor("#f2f2f2"));

        this.setWeightSum(5);

        this.topic =  new TextView(this.getContext());
        this.topic.setTypeface(null, Typeface.BOLD);
        this.addView(topic);
        this.topic.setText(String.valueOf(model.getTopic()));

        this.comments = new TextView(this.getContext());
        this.comments.setTypeface(null, Typeface.ITALIC);
        this.addView(comments);

        if (model.getComments() != null) {
            this.comments.setText(" - " + model.getComments());
        } else {
            this.comments.setText("  ");
        }

        this.removeTopic = new ImageButton(this.getContext());
        this.removeTopic.setImageDrawable(getResources().getDrawable(ic_delete));
        this.addView(this.removeTopic);
        this.removeTopic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                dataSource = new PrayerDataSource(context);
                dataSource.open();
                dataSource.deletePrayerById(model.getId());
                dataSource.close();

                // Mise à jour de l'historique
                lrl.updateList();
            }
        });

        LayoutParams lpT = new LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1.5f);
        this.topic.setLayoutParams(lpT);
        LayoutParams lpC = new LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 3f);
        this.comments.setLayoutParams(lpC);
        LayoutParams lpTR = new LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 0.5f);
        this.removeTopic.setLayoutParams(lpTR);

        this.setMinimumHeight(45);
    }
}
