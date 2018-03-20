package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import org.advantiste.ffja.sud.gdc.mygdcapplication.R;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.BibleBook;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.WeeklyReading;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.WeeklyReadingDataSource;
import org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.fragments.model.HistoryRowItem;
import org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.fragments.model.HistoryRowItemBook;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;


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
    private TableLayout tableLayout;
    private SwipeMenuListView swipeMenuListView;

    public void populateHistory() {
        // Supprimer toutes les données du tableau afin de recréer tout proprement
        tableLayout.removeAllViews();

        dataSource = new WeeklyReadingDataSource(this.getContext());
        dataSource.open();
        //dataSource.deleteAllWeeklyReading();

        ArrayList<WeeklyReading> readings = new ArrayList<> ( dataSource.getAllWeeklyReading());

        for (WeeklyReading reading: readings) {
            tableLayout.addView(new HistoryRowItem(this.getContext(),reading));
            for (BibleBook book : reading.getReadingDetails ( ).keySet ( )) {
                tableLayout.addView ( new HistoryRowItemBook ( this.getContext ( ), reading, book ) );
            }
        }

/** TODO: ça ne fonctionne pas encore
        ArrayAdapter<WeeklyReading> weeklyReadingArrayAdapter =  new ArrayAdapter<WeeklyReading> ( this.getContext (),R.layout.custom_reading_history_line, readings);
        swipeMenuListView.setAdapter ( weeklyReadingArrayAdapter );
        SwipeMenuCreator creator;
        creator = new SwipeMenuCreator () {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        ReadingFragmentHistory.this.getContext ());
                // set item background
                openItem.setBackground(new ColorDrawable ( Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(90);
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        ReadingFragmentHistory.this.getContext ());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(90);
                // set a icon
                deleteItem.setIcon( R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

// set creator
        swipeMenuListView.setMenuCreator(creator);

        swipeMenuListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener () {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open
                        Log.d ( "TAF", "lo "+index);
                        break;
                    case 1:
                        // delete
                        Log.d ( "TAF", "lo "+index);
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_reading_history, container, false);





        tableLayout = (TableLayout) rootView.findViewById(R.id.reading_list_history);
        swipeMenuListView =  (SwipeMenuListView ) rootView.findViewById(R.id.reading_hitory_listview);





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
