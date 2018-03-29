package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.advantiste.ffja.sud.gdc.mygdcapplication.R;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.WeeklyReading;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.WeeklyReadingDataSource;
import org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.adapters.ExpandableListViewAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ReadingFragmentHistory} interface
 * to handle interaction events.
 * Use the {@link ReadingFragmentHistory#} factory method to
 * create an instance of this fragment.
 */
public class ReadingFragmentHistory extends Fragment {


    private ImageButton add_reading;

    private WeeklyReadingDataSource dataSource;

    private ExpandableListView historyListView;
    private ArrayList<WeeklyReading> readings;


    public void populateHistory() {
        // Supprimer toutes les données du tableau afin de recréer tout proprement

        dataSource = new WeeklyReadingDataSource(this.getContext());
        dataSource.open();
        //dataSource.deleteAllWeeklyReading();


        readings = new ArrayList<> ( dataSource.getAllWeeklyReading());

        historyListView.setAdapter ( new ExpandableListViewAdapter ( getContext (),readings ) );
        historyListView.setMinimumHeight ( readings.size ()*30 );

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       final  ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_reading_history, container, false);

        rootView.post(new Runnable() {
                      @Override
                      public void run() {
                          int height=rootView.getMeasuredHeight(); // for instance
                            System.out.println (height);
                      }
                  });



        historyListView =   rootView.findViewById(R.id.history_list_view);





        populateHistory();

        /* *** TODO : Remet à vide le SQLite de LECTURE
        dataSource = new WeeklyReadingDataSource(this.getContext());
        dataSource.open();
        dataSource.deleteAllWeeklyReading();
        /**/
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
