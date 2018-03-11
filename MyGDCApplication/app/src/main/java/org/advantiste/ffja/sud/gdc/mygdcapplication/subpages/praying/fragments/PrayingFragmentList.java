package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.praying.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import org.advantiste.ffja.sud.gdc.mygdcapplication.R;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.prayers.Prayer;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.prayers.PrayerDataSource;
import org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.praying.fragments.model.ListRowItem;

import java.util.Calendar;
import java.util.List;

/**
 * Created by erc on 23/02/18.
 */

public class PrayingFragmentList extends Fragment {


    private PrayerDataSource dataSource;
    private TableLayout tableLayout;

    public void populateListPrayer() {
        tableLayout.removeAllViews();
        dataSource = new PrayerDataSource(this.getContext());
        dataSource.open();
        List<Prayer> prayers = dataSource.getAllPrayers();
        int cpt = 0;

        for (Prayer prayer: prayers) {
            tableLayout.addView(new ListRowItem(this.getContext(), prayer, cpt, new ListRowItem.ListRowListener() {
                @Override
                public void updateList() {
                    PrayingFragmentList.this.populateListPrayer();
                }
            }));
            cpt ++;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_praying_list, container, false);
        tableLayout = (TableLayout) rootView.findViewById(R.id.praying_list_topics);

        /* Remove all prayers
        dataSource = new PrayerDataSource(this.getContext());
        dataSource.open();
        dataSource.deleteAllPrayers();
        dataSource.close();
        /* */

        //TODO : ajouter le bouton qui va avec

        /*

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        long beginDate = calendar.getTimeInMillis()-3*604800000;
        calendar.setTimeInMillis(beginDate+604800000); //oneWeek);
        long endDate =  calendar.getTimeInMillis();
        dataSource.createPrayer(beginDate, -1, "Mega TILT",null);
        dataSource.createPrayer(beginDate, endDate, "Julie", "boulot");
        beginDate = endDate;
        endDate += 604800000;
        dataSource.createPrayer(beginDate, endDate, "Bob", "études");
        beginDate = endDate;
        dataSource.createPrayer(beginDate, -1, "Assemblée en ARLES",null);
        */

        populateListPrayer();
        return rootView;
    }

    @Override
    public void onResume() {
        dataSource.open();
        super.onResume();
    }

    @Override
    public void onPause() {
        dataSource.close();
        super.onPause();
    }

}
